package blaybus.domain.designer.presentation.dto.response;

import java.util.List;

public record DesignerResponseDTO(
        String id,
        String name,
        String profile,
        String address,
        String expertField,
        String introduce,
        List<String> portfolios,
        String type,
        int offlinePrice,
        int onlinePrice
) {}