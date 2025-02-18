package blaybus.domain.map.application.service.impl;

import blaybus.domain.map.application.service.PositionDistanceCalculateService;
import blaybus.domain.map.domain.repository.PositionRepository;
import blaybus.domain.map.presentation.dto.response.PositionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionDistanceCalculateServiceImpl implements PositionDistanceCalculateService {

    private final PositionRepository positionRepository;

    @Override
    public List<PositionResponseDTO> orderPositionByDistance(double lat, double lng) {
        return positionRepository.findAllOrderByDistance(lat, lng).stream()
                .map(row -> new PositionResponseDTO((String) row[0], (Double) row[1])).toList();
    }
}
