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

        // ✅ DTO에서 position.name 값만 추출하여 String 리스트로 변환
        List<String> positionNames = positions.stream()
                .map(PositionResponseDTO::name) // DTO에서 name 필드만 가져오기
                .collect(Collectors.toList());

        // ✅ String 리스트로 변환하여 Repository 호출
        List<Designer> designers = designerRepository.findAllByPositionNameAndTypeInOrderByCustomOrder(positionNames);

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
                    designer.getExpertField().toString(),
                    designer.getIntroduce(),
                    designer.getPortfolios(),
                    designer.getType().toString(),
                    designer.getOfflinePrice(),
                    designer.getOnlinePrice()
            );
        }).collect(Collectors.toList());
    }
}