package blaybus.domain.meeting.application.service;

import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;

public interface GoogleMeetService {
    MeetingResponse createMeeting(MeetingCreateRequest request);
}
