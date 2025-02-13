package blaybus.domain.meeting.application.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface GoogleMeetService {
    MeetingResponse createMeeting(MeetingCreateRequest request, OAuth2AuthenticationToken authentication);
}
