package blaybus.domain.consulting.presentation;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingStatus;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingTime;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingType;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultingDTO {

    private Long id;
    private String userId;
    private String designerId;
    private int addressId;
    private ConsultingType type;
    private ConsultingTime time;
    private ConsultingStatus status;

    // 엔티티 -> DTO 변환
    public static ConsultingDTO fromEntity(Consulting consulting) {
        return ConsultingDTO.builder()
                .id(consulting.getId())
                .userId(consulting.getUser().getId())
                .designerId(consulting.getDesigner().getId())
                .addressId(consulting.getAddressId())
                .type(consulting.getType())
                .time(consulting.getTime())
                .status(consulting.getStatus())
                .build();
    }

    // DTO -> 엔티티 변환
    public Consulting toEntity(User user, Designer designer) {
        return Consulting.builder()
                .id(this.id)
                .user(user)
                .designer(designer)
                .addressId(this.addressId)
                .type(this.type)
                .time(this.time)
                .status(this.status)
                .build();
    }
}
