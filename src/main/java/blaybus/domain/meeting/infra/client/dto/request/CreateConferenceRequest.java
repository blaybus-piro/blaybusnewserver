package blaybus.domain.meeting.infra.client.dto.request;

public record CreateConferenceRequest(
        String requestId,
        ConferenceSolutionKey conferenceSolutionkey
) {
}
