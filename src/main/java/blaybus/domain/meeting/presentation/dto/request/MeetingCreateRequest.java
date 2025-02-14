package blaybus.domain.meeting.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MeetingCreateRequest(
        @NotNull
        String title,

        @NotNull
        LocalDateTime startTime,

        @NotNull
        LocalDateTime endTime
) {}