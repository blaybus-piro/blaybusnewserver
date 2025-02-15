package blaybus.domain.consulting.presentation;

import blaybus.domain.consulting.domain.entity.Consulting;

public record ConsultingDTO(
        int id,
        String userId,
        String designerId,
        int addressId,
        long meetId,
        Consulting.ConsultingType type,
        Consulting.ConsultingStatus status
) {
    public static ConsultingDTO fromEntity(Consulting consulting) {
        return new ConsultingDTO(
                consulting.getId(),
                consulting.getUser().getId(),
                consulting.getDesigner().getId(),
                consulting.getPosition().getId(),
                consulting.getMeeting().getId(),
                consulting.getType(),
                consulting.getStatus()
        );
    }
}
