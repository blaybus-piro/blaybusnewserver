package blaybus.domain.meeting.application.service;

import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;

public interface MeetingService {
    MeetingResponse createMeeting(String userId, MeetingCreateRequest request);
}
