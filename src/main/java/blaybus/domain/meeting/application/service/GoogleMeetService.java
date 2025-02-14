package blaybus.domain.meeting.application.service;

import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface GoogleMeetService {
    MeetingResponse createMeeting(MeetingCreateRequest request);
}
