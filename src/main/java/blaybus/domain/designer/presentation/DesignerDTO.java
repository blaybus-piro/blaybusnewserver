package blaybus.domain.designer.presentation;

import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.entity.Designer.ExpertField;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

// 예약 관련해서 연결할 가능성이 높으므로 record 사용 안함

public class DesignerDTO {

    private String id;
    private String name;
    private String profile;
    private String address;
    private ExpertField expertField;
    private String introduce;
    private int addressId;
    private String portfolio;

    // Entity -> DTO 변환
    public static DesignerDTO fromEntity(Designer designer) {
        return DesignerDTO.builder()
                .id(designer.getId())
                .name(designer.getName())
                .profile(designer.getProfile())
                .address(designer.getAddress())
                .expertField(designer.getExpertField())
                .introduce(designer.getIntroduce())
                .addressId(designer.getAddressId())
                .portfolio(designer.getPortfolio())
                .build();
    }
}