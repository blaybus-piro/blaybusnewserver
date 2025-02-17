package blaybus.domain.meeting.infra.feignclient.dto.request;

public record ConferenceRequest(
        String summary,
        EventDateTime start,
        EventDateTime end,
        ConferenceData conferenceData,
        int conferenceDataVersion // 기본값이 1이어야 Google Meet 링크 생성 가능
) {
    public ConferenceRequest {
        if (conferenceDataVersion == 0) {
            conferenceDataVersion = 1;  // 기본값 설정
        }
    }
}