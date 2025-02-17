package blaybus.domain.meeting.infra.feignclient.dto.response;

public record ConferenceResponse(
        String id,
        String hangoutLink,
        ConferenceData conferenceData
) {}