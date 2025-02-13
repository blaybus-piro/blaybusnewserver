package blaybus.domain.meeting.infra.client.dto.request;

public record ConferenceSolutionKey(String type) {
    public ConferenceSolutionKey() {
        this("hangoutsMeet");
    }
}
