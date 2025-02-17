package blaybus.domain.meeting.infra.feignclient.dto.response;

public record ConferenceSolution(
        Key key,
        String name,
        String iconUri
) {
}
