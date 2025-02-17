package blaybus.domain.meeting.infra.feignclient.dto.response;

public record CreateConferenceRequest(
        String requestId,
        ConferenceSolutionKey conferenceSolutionKey,
        Status status
) {
}
