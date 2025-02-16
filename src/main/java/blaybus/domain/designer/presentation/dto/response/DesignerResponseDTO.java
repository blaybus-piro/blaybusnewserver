package blaybus.domain.designer.presentation.dto.response;

import blaybus.domain.designer.domain.entity.Area;
import blaybus.domain.designer.domain.entity.ExpertField;
import blaybus.domain.designer.domain.entity.Type;

public record DesignerResponseDTO(
        String id,
        String name,
        String profile,
        Area area,
        ExpertField expertField,
        String introduce,
        String portfolio,
        Type type,
        int offlinePrice,
        int onlinePrice
) {}

