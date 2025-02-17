package blaybus.domain.map.application.service;

import java.util.List;

public interface PositionDistanceCalculateService {
    List<String> orderPositionByDistance(double lat, double lng);
}
