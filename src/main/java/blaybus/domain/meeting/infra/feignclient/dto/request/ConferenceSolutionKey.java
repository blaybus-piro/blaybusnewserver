package blaybus.domain.meeting.infra.feignclient.dto.request;

public record ConferenceSolutionKey(
        String type
) {
    public ConferenceSolutionKey() {
        this("hangoutsMeet"); // 기본값 설정
    }
}
