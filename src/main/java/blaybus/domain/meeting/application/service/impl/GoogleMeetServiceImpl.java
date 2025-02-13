package blaybus.domain.meeting.application.service.impl;

import blaybus.domain.meeting.application.service.GoogleMeetService;
import blaybus.domain.meeting.infra.client.GoogleMeetClient;
import blaybus.domain.meeting.infra.client.dto.request.ConferenceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleMeetServiceImpl implements GoogleMeetService {
    private final GoogleMeetClient googleMeetClient;

    @Override
    public MeetingResponse createMeeting(MeetingCreateRequest request, OAuth2AuthenticationToken authentication) {
        try {
            String token = "Bearer " + authentication.getPrincipal().getAttribute("access_token");
            ConferenceResponse response = googleMeetClient.createMeeting(
                    token,
                    createConferenceRequest(request)
            );
            return new MeetingResponse(response.getHangoutLink());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Google Meet link", e);
        }
    }

    private ConferenceRequest createConferenceRequest(MeetingCreateRequest request) {
        ConferenceRequest conferenceRequest = new ConferenceRequest();
        ConferenceRequest.setSummary(request.getTitle());

        // 시작 시간 설정
        ConferenceRequest.EventDateTime start = new ConferenceRequest.EventDateTime();
        start.setDateTime(request.getStartTime().toString());
        conferenceRequest.setStart(start);
    }
}
