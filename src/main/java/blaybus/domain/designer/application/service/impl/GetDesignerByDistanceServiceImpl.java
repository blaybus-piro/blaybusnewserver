package blaybus.domain.designer.application.service.impl;

import blaybus.domain.designer.application.service.GetDesignerByDistanceService;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.map.application.service.PositionDistanceCalculateService;
import blaybus.domain.map.presentation.dto.response.PositionResponseDTO;
import blaybus.domain.designer.presentation.dto.response.DesignerDistanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDesignerByDistanceServiceImpl implements GetDesignerByDistanceService {

    private final PositionDistanceCalculateService positionDistanceCalculateService;
    private final DesignerRepository designerRepository;

    @Override
    public List<DesignerDistanceResponseDTO> getDesignersByLocation(double lat, double lng) {
        List<PositionResponseDTO> positions = positionDistanceCalculateService.orderPositionByDistance(lat, lng);
        List<Designer> designers = designerRepository.findAllByPositionNameAndTypeInOrderByCustomOrder(positions);

        return designers.stream().map(designer -> {
            double distance = positions.stream()
                    .filter(pos -> pos.name().equals(designer.getPosition().getName()))
                    .findFirst()
                    .map(PositionResponseDTO::distance)
                    .orElse(0.0);
            return new DesignerDistanceResponseDTO(
                    designer.getId(),
                    designer.getName(),
                    designer.getProfile(),
                    designer.getPosition().getName(),
                    distance,
                    designer.getExpertField(),
                    designer.getIntroduce(),
                    designer.getPortfolios(),
                    designer.getType(),
                    designer.getOfflinePrice(),
                    designer.getOnlinePrice()
            );
        }).collect(Collectors.toList());
    }
}
