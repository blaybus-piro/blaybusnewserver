package blaybus.domain.consulting.presentation.dto.request;

import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import jakarta.validation.constraints.NotNull;

public record ConsultingStatusUpdateDTO(
        @NotNull long id,
        @NotNull ConsultingStatus consultingStatus
) {}