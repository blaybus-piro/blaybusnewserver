package blaybus.domain.meeting.infra.feignclient.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConferenceRequest {
    private String title;
    private EventDateTime start;
    private EventDateTime end;
    private ConferenceData conferenceData;

    @Getter @Setter
    public static class EventDateTime {
        private String dateTime;
        private String timeZone = "Asia/Seoul";
    }

    @Getter @Setter
    public static class ConferenceData {
        private CreateConferenceRequest createRequest;
    }

    @Getter @Setter
    public static class CreateConferenceRequest {
        private String requestId;
        private ConferenceSolutionKey conferenceSolutionKey;
    }

    @Getter @Setter
    public static class ConferenceSolutionKey {
        private String type = "hangoutsMeet"; // API 호환성 유지
    }
}
