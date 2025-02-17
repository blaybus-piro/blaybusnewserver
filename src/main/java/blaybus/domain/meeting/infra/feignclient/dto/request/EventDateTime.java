package blaybus.domain.meeting.infra.feignclient.dto.request;

public record EventDateTime(
        String dateTime,
        String timeZone
) {
    public EventDateTime(String dateTime) {
        this(dateTime, "Asia/Seoul");
    }
}
