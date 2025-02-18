package blaybus.domain.map.application.service;

import blaybus.domain.map.presentation.dto.response.PositionResponseDTO;

import java.util.List;

public interface PositionDistanceCalculateService {
    List<PositionResponseDTO> orderPositionByDistance(double lat, double lng);
}