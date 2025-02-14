package blaybus.domain.metting.presentation.dto.google.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoogleEventResponse {
    private String id;
    private String summary;
    private String description;
    private String hangoutLink; // Google Meet 링크가 여기 들어오는 경우가 많음

    private ConferenceDataResponse conferenceData;

    // 필요시 더 많은 필드 추가
}