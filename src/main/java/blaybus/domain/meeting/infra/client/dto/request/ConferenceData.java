package blaybus.domain.meeting.infra.client.dto.request;

import blaybus.domain.meeting.infra.client.dto.response.CreateConferenceRequest;

public record ConferenceData(
        CreateConferenceRequest createRequest
) {
}
