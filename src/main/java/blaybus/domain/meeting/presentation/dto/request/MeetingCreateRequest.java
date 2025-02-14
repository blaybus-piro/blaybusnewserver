package blaybus.domain.meeting.presentation.dto.request;

import java.time.LocalDateTime;

public record MeetingCreateRequest(
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}