package blaybus.domain.meeting.infra.feignclient.dto.response;

import java.util.List;

public record ConferenceData(
        String conferenceId,
        ConferenceSolution conferenceSolution,
        CreateConferenceRequest createRequest,
        List<EntryPoints> entryPoints
) {
}
