package blaybus.domain.consulting.presentation.dto.response;


import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.meeting.domain.entity.Meeting;

import java.time.LocalDateTime;

public record ConsultingResponseDTO(
        Long consultingId,
        String userId,
        String designerId,
        Meeting meeting,
        //대면 비대면
        ConsultingType meet,

        // 4가지 상태
        ConsultingStatus status,
        LocalDateTime time
) {
}

