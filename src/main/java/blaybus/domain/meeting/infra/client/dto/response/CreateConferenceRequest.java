package blaybus.domain.meeting.infra.client.dto.response;

public record CreateConferenceRequest(
        String requestId,
        ConferenceSolutionKey conferenceSolutionKey,
        Status status
) {}
