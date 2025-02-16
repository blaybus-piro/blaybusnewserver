package blaybus.domain.consulting.presentation.dto.response;


import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.meeting.entity.Meeting;
import jakarta.validation.constraints.NotNull;

public record ConsultingResponseDTO(
        @NotNull long id,
        @NotNull String userId,
        @NotNull String designerId,
        Meeting meeting,
        @NotNull ConsultingType type,
        ConsultingStatus status
)
{ }

