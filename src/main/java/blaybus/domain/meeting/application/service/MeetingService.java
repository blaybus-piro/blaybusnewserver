package blaybus.domain.meeting.application.service;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;

import java.time.LocalDateTime;

public interface MeetingService {
    MeetingResponse createMeeting(String userId, LocalDateTime startTime, Designer designer);
}
