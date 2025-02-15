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
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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

    private String getValidAccessToken(String userId) {
        GoogleJsonWebToken token = googleTokenRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Google 토큰을 찾을 수 없습니다."));

        if (token.getExpiresIn().minusMinutes(10).isBefore(LocalDateTime.now())) {
            OAuth2TokenResponse newToken = tokenService.refreshAccessToken(token.getRefreshToken());

            // 새 토큰 저장
            GoogleJsonWebToken updatedToken = GoogleJsonWebToken.builder()
                    .userId(userId)
                    .accessToken(newToken.accessToken())
                    .refreshToken(token.getRefreshToken()) // refresh token은 유지
                    .expiresIn(LocalDateTime.now().plusHours(1))
                    .build();
            googleTokenRepository.save(updatedToken);

            return "Bearer " + newToken.accessToken();
        }
        return "Bearer " + token.getAccessToken();
    }

    @Override
    public MeetingResponse createMeeting(String userId, MeetingCreateRequest request) {
        String accessToken = getValidAccessToken(userId);
        String meetingTitle = generateMeetingTitle(request.startTime());

        try {
            ConferenceResponse response = googleMeetClient.createMeeting(
                    accessToken,
                    createConferenceRequest(meetingTitle, request.startTime())
            );

            Meeting meeting = createMeeting(request, meetingTitle, response.getHangoutLink());
            meetingRepository.save(meeting);

            return new MeetingResponse(meetingTitle, response.getHangoutLink());

        } catch (Exception e) {
            throw new RuntimeException("Google Meet 링크 생성에 실패했습니다.", e);
        }
    }

    private String generateMeetingTitle(LocalDateTime startTime) {
        return String.format("상담 예약 - %s",
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

    private ConferenceRequest createConferenceRequest(String title, LocalDateTime startTime) {
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        conferenceRequest.setTitle(title);

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.toString());
        conferenceRequest.setStart(start);

        // 종료 시간 설정 -> api 통신상 필수
        ConferenceRequest.EventDateTime end = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.plusHours(1).toString());
        conferenceRequest.setEnd(end);

        return conferenceRequest;
    }
}

