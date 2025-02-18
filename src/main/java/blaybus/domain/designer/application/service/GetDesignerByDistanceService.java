package blaybus.domain.designer.application.service;

import blaybus.domain.designer.presentation.dto.response.DesignerDistanceResponseDTO;

import java.util.List;

public interface GetDesignerByDistanceService {
    List<DesignerDistanceResponseDTO> getDesignersByLocation(double lat, double lng);
}