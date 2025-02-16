package blaybus.domain.consulting.presentation.dto.request;

import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.meeting.domain.entity.Meeting;
import jakarta.validation.constraints.NotNull;

public record ConsultingRequestDTO(

        // 리액트에서 임의의값 해서 보내주면 될듯
        String designerId,
        //대면 비대면
        String meet,

        // 4가지 상태
        String status,

        // 사용자가 예약한 시간
        String startTime
) {

}

