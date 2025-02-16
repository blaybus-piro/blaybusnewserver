package blaybus.domain.meeting.presentation.dto.response;

public record MeetingResponse(
        String title,
        String hangoutLink,

        Long id
) {}
