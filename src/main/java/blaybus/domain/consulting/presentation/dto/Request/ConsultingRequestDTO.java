package blaybus.domain.consulting.presentation.dto.Request;

import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.meeting.entity.Meeting;
import jakarta.validation.constraints.NotNull;

public record ConsultingRequestDTO(
        @NotNull String userId,
        @NotNull String designerId,
        Meeting meeting,
        @NotNull ConsultingType type,
        ConsultingStatus status
)
{
    public ConsultingRequestDTO {
        // 기본값 설정 (null 방지)
        if (status == null) {
            status = ConsultingStatus.FREE;
        }
    }
}
