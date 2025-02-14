package blaybus.domain.meeting.application.service.impl;

import blaybus.domain.meeting.application.service.GoogleMeetService;
import blaybus.domain.meeting.infra.client.GoogleMeetClient;
import blaybus.domain.meeting.infra.client.dto.request.ConferenceRequest;
import blaybus.domain.meeting.infra.client.dto.response.ConferenceResponse;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleMeetServiceImpl implements GoogleMeetService {
    private final GoogleMeetClient googleMeetClient;

    @Override
    public MeetingResponse createMeeting(MeetingCreateRequest request) {
        try {
            ConferenceResponse response = googleMeetClient.createMeeting(
                    createConferenceRequest(request)
            );
            return new MeetingResponse(response.getHangoutLink());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Google Meet link", e);
        }
    }

    private ConferenceRequest createConferenceRequest(MeetingCreateRequest request) {
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        conferenceRequest.setSummary(request.title());

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(request.startTime().toString());
        conferenceRequest.setStart(start);

        return conferenceRequest;
    }
}
