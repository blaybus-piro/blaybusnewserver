package blaybus.domain.time.presentation.dto.res.detailtime.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultingDetail {
    // 예약 시간
    String startTime;

    // 4가지 상태
    String status;

    // 비대면 대면
    String meet;

    // String 결제 방식
}
