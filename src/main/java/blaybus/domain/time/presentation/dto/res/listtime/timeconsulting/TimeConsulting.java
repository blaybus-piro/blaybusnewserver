package blaybus.domain.time.presentation.dto.res.listtime.timeconsulting;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimeConsulting {

    // 컨설팅 ID
    Long id;

    // 예약 시간
    LocalDateTime startTime;

    // 대면 비대면
    String meet;

    // 예약 상태
    String status;
}
