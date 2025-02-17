package blaybus.domain.designer.presentation.dto.response;

import blaybus.domain.designer.domain.entity.ExpertField;
import blaybus.domain.designer.domain.entity.Type;

public record DesignerResponseDTO(
        String id,
        String name,
        String profile,
        String address,
        ExpertField expertField,
        String introduce,
        String portfolio,
        Type type,
        int offlinePrice,
        int onlinePrice
) {}

