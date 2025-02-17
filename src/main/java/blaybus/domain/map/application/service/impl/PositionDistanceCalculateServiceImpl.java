package blaybus.domain.map.application.service.impl;

import blaybus.domain.map.application.service.PositionDistanceCalculateService;
import blaybus.domain.map.domain.entity.Position;
import blaybus.domain.map.domain.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionDistanceCalculateServiceImpl implements PositionDistanceCalculateService {

    private final PositionRepository positionRepository;

    @Override
    public List<String> orderPositionByDistance(double lat, double lng) {
        List<Position> distances = positionRepository.findAllOrderByDistance(lat, lng);
        return distances.stream().map(Position::getName).collect(Collectors.toList());
    }
}
