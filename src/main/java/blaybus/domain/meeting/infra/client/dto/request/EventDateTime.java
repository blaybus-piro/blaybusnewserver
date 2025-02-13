package blaybus.domain.meeting.infra.client.dto.request;

public record EventDateTime(
        String dateTime,
        String timeZone
) {
    public EventDateTime(String dateTime) {
        this(dateTime, "Asia/Seoul"); // 기본값 설정
    }
}
