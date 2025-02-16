package blaybus.domain.consulting.presentation.dto.Request;

import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import jakarta.validation.constraints.NotNull;

public record ConsultingStatusUpdateDTO(
        @NotNull long Id,
        @NotNull ConsultingStatus consultingStatus
) {}