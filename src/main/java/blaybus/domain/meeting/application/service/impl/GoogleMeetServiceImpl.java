package blaybus.domain.meeting.application.service.impl;

import blaybus.domain.meeting.application.service.GoogleMeetService;
import blaybus.domain.meeting.infra.feignclient.GoogleMeetClient;
import blaybus.domain.meeting.infra.feignclient.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.feignclient.dto.response.ConferenceResponse;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class GoogleMeetServiceImpl implements GoogleMeetService {
    private final GoogleMeetClient googleMeetClient;
    private final DesignerRepository designerRepository;

    @Override
    public MeetingResponse createMeeting(MeetingCreateRequest request) {
        // 디자이너 이름을 가져와야 함... Id로 조회
        Designer designer = designerRepository.findById(request.designerId())
                .orElseThrow(() -> new EntityNotFoundException("디자이너를 찾을 수 없습니다."));

        String meetingTitle = createMeetingTitle(designer.getName(), request.startTime());

        try {
            ConferenceResponse response = googleMeetClient.createMeeting(
                    createConferenceRequest(meetingTitle, request.startTime())
            );
            return new MeetingResponse(meetingTitle, response.getHangoutLink());
        } catch (Exception e) {
            throw new RuntimeException("Google Meet 링크 생성에 실패했습니다.", e);
        }
    }

    private String createMeetingTitle(String designerName, LocalDateTime startTime) {
        return String.format(
                "%s 디자이너님과의 상담 - %s",
                designerName,
                startTime.format(DateTimeFormatter.ofPattern("M월 d일 a h시 mm분"))
        );
    }

    private ConferenceRequest createConferenceRequest(String title, LocalDateTime startTime) {
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        conferenceRequest.setTitle(title);

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(startTime.toString());
        conferenceRequest.setStart(start);

        return conferenceRequest;
    }
}
