package blaybus.domain.designer.presentation.DTO;

import blaybus.domain.designer.domain.entity.Designer;

import java.util.List;

public record DesignerDTO(
        String id,
        String name,
        String profile,
        Designer.Area area,
        Designer.ExpertField expertField,
        String introduce,
        int addressId,
        String portfolio,
        List<Designer.Type> types, // Set -> List 변환 (가독성 향상)
        int offlinePrice,
        int onlinePrice
)
{
    public static DesignerDTO fromEntity(Designer designer) {
        return new DesignerDTO(
                designer.getId(),
                designer.getName(),
                designer.getProfile(),
                designer.getArea(),
                designer.getExpertField(),
                designer.getIntroduce(),
                designer.getPosition().getId(),
                designer.getPortfolio(),
                List.copyOf(designer.getTypes()), // Set -> List 변환
                designer.getOfflinePrice(),
                designer.getOnlinePrice()
        );
    }
}
