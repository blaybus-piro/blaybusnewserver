package blaybus.domain.meeting.infra.feignclient.dto.request;

public record CreateConferenceRequest(
        String requestId, // UUID
        ConferenceSolutionKey conferenceSolutionKey
) {
}
