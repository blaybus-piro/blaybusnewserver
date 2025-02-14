package blaybus.domain.metting.presentation.dto.google.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleEventRequest {
    private String summary;           // 이벤트 제목
    private String description;       // 이벤트 설명
    private EventDateTime start;      // 시작 일시
    private EventDateTime end;        // 종료 일시

    // 화상회의 생성 요청
    private ConferenceData conferenceData;

    // 필요하다면 참석자, location 등 다른 필드 추가 가능
}