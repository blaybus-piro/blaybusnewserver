package blaybus.domain.meeting.infra.client.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConferenceResponse {
    private String id;
    private String hangoutLink;
    private ConferenceData conferenceData;

    @Getter @Setter
    public static class ConferenceData {
        private String conferenceId;
        private ConferenceSolution conferenceSolution;
        private CreateConferenceRequest createRequest;
        private EntryPoints entryPoints;
    }

    @Getter @Setter
    public static class ConferenceSolution {
        private Key key;
        private String name;
        private String iconUri;
    }

    @Getter @Setter
    public static class Key {
        private String type;
    }

    @Getter @Setter
    public static class CreateConferenceRequest {
        private String requestId;
        private ConferenceSolutionKey conferenceSolutionKey;
        private Status status;
    }

    @Getter @Setter
    public static class ConferenceSolutionKey {
        private String type;
    }

    @Getter @Setter
    public static class Status {
        private String statusCode;
    }

    @Getter @Setter
    public static class EntryPoints {
        private String uri;
        private String label;
    }
}
