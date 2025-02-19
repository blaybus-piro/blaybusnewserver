package blaybus.domain.consulting.presentation.dto.request;

import java.time.LocalDateTime;

public record ConsultingRequestDTO(

        // 리액트에서 임의의값 해서 보내주면 될듯
        String designerId,
        //대면 비대면
        String meet,

        // 결제 방식
        String pay,

        // 사용자가 예약한 시간
        LocalDateTime startTime
) {

}

