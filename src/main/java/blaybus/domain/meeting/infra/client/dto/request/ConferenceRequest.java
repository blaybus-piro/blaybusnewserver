package blaybus.domain.meeting.infra.client.dto.request;

public record ConferenceRequest(
        String summary,
        EventDateTime start,
        EventDateTime end,
        ConferenceData conferenceData
) {}