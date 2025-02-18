package blaybus.domain.designer.presentation.dto.response;

import blaybus.domain.designer.domain.entity.ExpertField;
import blaybus.domain.designer.domain.entity.Type;

import java.util.List;

public record DesignerDistanceResponseDTO(
        String id,
        String name,
        String profile,
        String address,
        double distance, // 추가된 거리 정보
        ExpertField expertField,
        String introduce,
        List<String> portfolios,
        Type type,
        int offlinePrice,
        int onlinePrice
) {}
