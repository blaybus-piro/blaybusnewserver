package blaybus.domain.designer.presentation;

import blaybus.domain.designer.domain.entity.Designer;

import java.util.List;
import java.util.Set;

public record DesignerDTO(
        String id,
        String name,
        String profile,
        Designer.Area area,
        Designer.ExpertField expertField,
        String introduce,
        String addressId,
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
                designer.getPosition().getName(),
                designer.getPortfolio(),
                List.copyOf(designer.getTypes()), // Set -> List 변환
                designer.getOfflinePrice(),
                designer.getOnlinePrice()
        );
    }
}
