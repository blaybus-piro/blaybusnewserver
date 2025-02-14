package blaybus.domain.metting.presentation.dto.google.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDateTime {
    private String dateTime;  // "2023-10-01T09:00:00+09:00" 형태
    private String timeZone;  // "Asia/Seoul" 등
}
