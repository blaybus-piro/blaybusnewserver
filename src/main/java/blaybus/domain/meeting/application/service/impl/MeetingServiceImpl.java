package blaybus.domain.meeting.application.service.impl;

import blaybus.domain.meeting.application.service.MeetingService;
import blaybus.domain.meeting.entity.Meeting;
import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import blaybus.domain.meeting.repository.MeetingRepository;
import blaybus.domain.oauth2.application.service.GoogleAccessTokenAndRefreshTokenService;
import blaybus.domain.oauth2.application.service.GoogleTokenService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.global.infra.exception.BlaybusException;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {
    private final GoogleMeetClient googleMeetClient;
    private final GoogleJsonWebTokenRepository googleTokenRepository;
    private final GoogleAccessTokenAndRefreshTokenService tokenService;
    private final MeetingRepository meetingRepository;
    private final GoogleTokenService googleTokenService;

    @Override
    public MeetingResponse createMeeting(String userId, MeetingCreateRequest request) {
        // 시간 유효성 검사
        if (request.startTime().isBefore(LocalDateTime.now())) {
            throw new BlaybusException(HttpStatus.BAD_REQUEST, "시작 시간은 현재보다 미래여야 합니다.");
        }

        if (request.endTime().isBefore(request.startTime())) {
            throw new BlaybusException(HttpStatus.BAD_REQUEST, "종료 시간은 시작 시간보다 뒤여야 합니다.");
        }
        String accessToken = googleTokenService.getValidAccessToken(userId);
        String meetingTitle = generateMeetingTitle(request.designerName(), request.startTime());

        try {
            ConferenceResponse response = googleMeetClient.createMeeting(
                    accessToken,
                    createConferenceRequest(meetingTitle, request.startTime(), request.endTime())
            );

            Meeting meeting = createMeeting(request, meetingTitle, response.getHangoutLink());
            meetingRepository.save(meeting);

            return new MeetingResponse(meetingTitle, response.getHangoutLink());

        } catch (Exception e) {
            throw new RuntimeException("Google Meet 링크 생성에 실패했습니다.", e);
        }
    }

    public String generateMeetingTitle(String designerName, LocalDateTime startTime) {
        return String.format("%s님과의 상담 예약 - %s",
                designerName,
                startTime.format(DateTimeFormatter.ofPattern("M월 d일 a h시 mm분")));
    }

    private Meeting createMeeting(MeetingCreateRequest request, String title, String meetUrl) {
        return Meeting.builder()
                .title(title)
                .meetUrl(meetUrl)
                .startTime(request.startTime())
                .endTime(request.startTime().plusHours(1))
                .build();
    }

    private ConferenceRequest createConferenceRequest(String title, LocalDateTime startTime, LocalDateTime endTime) {
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        conferenceRequest.setSummary(title);

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.toString());
        conferenceRequest.setStart(start);

        // 종료 시간 설정 -> api 통신상 필수
        endTime = startTime.plusHours(1); // 구글밋 기본 1시간 통화 반영

        ConferenceRequest.EventDateTime end = new ConferenceRequest.EventDateTime();
        end.setDateTime(endTime.toString());
        conferenceRequest.setEnd(end);

        return conferenceRequest;
    }
}

