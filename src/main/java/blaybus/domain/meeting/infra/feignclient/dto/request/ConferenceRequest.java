package blaybus.domain.meeting.infra.feignclient.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ConferenceRequest {
    private String summary;
    private EventDateTime start;
    private EventDateTime end;
    private ConferenceData conferenceData;
    private int conferenceDataVersion = 1; // Google Meet 자동 생성 필수 설정

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDateTime {
        private String dateTime;
        private String timeZone = "Asia/Seoul";
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConferenceData {
        private CreateConferenceRequest createRequest;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateConferenceRequest {
        private String requestId; // UUID 값
        private ConferenceSolutionKey conferenceSolutionKey;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConferenceSolutionKey {
        private String type = "hangoutsMeet"; // Google Meet 사용
    }
}
