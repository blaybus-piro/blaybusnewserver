package blaybus.domain.meeting.infra.client.dto.response;

import blaybus.domain.meeting.infra.client.dto.response.ConferenceData;

public record ConferenceResponse(
        String id,
        String hangoutLink,
        ConferenceData conferenceData
) {}