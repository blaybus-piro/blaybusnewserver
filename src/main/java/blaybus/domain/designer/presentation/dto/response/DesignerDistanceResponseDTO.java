package blaybus.domain.designer.presentation.dto.response;

import java.util.List;

public record DesignerDistanceResponseDTO(
        String id,
        String name,
        String profile,
        String address,
        double distance, // 추가된 거리 정보
        String expertField,
        String introduce,
        List<String> portfolios,
        String type,
        int offlinePrice,
        int onlinePrice
) {}
