package blaybus.domain.meeting.infra.client.dto.response;

public record ConferenceData(
        String conferenceId,
        ConferenceSolution conferenceSolution,
        CreateConferenceRequest createRequest,
        EntryPoints entryPoints
) {}
