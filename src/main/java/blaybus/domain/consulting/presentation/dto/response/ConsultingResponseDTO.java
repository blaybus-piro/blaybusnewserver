package blaybus.domain.consulting.presentation.dto.response;


import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.meeting.entity.Meeting;
import jakarta.validation.constraints.NotNull;

public record ConsultingResponseDTO(
        Long id,
        String userId,
        String designerId,
        Meeting meeting,
        //대면 비대면
        String meet,

        // 4가지 상태
        String status
) {
}

