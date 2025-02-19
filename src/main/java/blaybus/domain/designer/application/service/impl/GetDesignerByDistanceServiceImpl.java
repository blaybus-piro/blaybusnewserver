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
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDesignerByDistanceServiceImpl implements GetDesignerByDistanceService {

    private final PositionDistanceCalculateService positionDistanceCalculateService;
    private final DesignerRepository designerRepository;

    @Override
    public List<DesignerDistanceResponseDTO> getDesignersByLocation(double lat, double lng) {
        // ✅ 위치별 거리 계산하여 PositionResponseDTO 리스트 획득
        List<PositionResponseDTO> positions = positionDistanceCalculateService.orderPositionByDistance(lat, lng);

        // ✅ PositionResponseDTO 리스트에서 position.name 값만 추출하여 리스트 생성
        List<String> positionNames = positions.stream()
                .map(PositionResponseDTO::name)
                .collect(Collectors.toList());

        // ✅ String 리스트를 기반으로 Repository 호출 (positionNames 순서 유지)
        List<Designer> designers = designerRepository.findAllByPositionNameOrderByCustomOrder(positionNames);

        // ✅ positions 리스트를 Map으로 변환 (key: positionName, value: distance)
        Map<String, Double> positionDistanceMap = positions.stream()
                .collect(Collectors.toMap(PositionResponseDTO::name, PositionResponseDTO::distance));

        // ✅ 디자이너 리스트를 DTO 리스트로 변환
        return designers.stream()
                .map(designer -> {
                    // ✅ Map에서 distance 가져오기 (없으면 기본값 0.0)
                    double distance = positionDistanceMap.getOrDefault(designer.getPosition().getName(), 0.0);

                    return new DesignerDistanceResponseDTO(
                            designer.getId(),
                            designer.getName(),
                            designer.getProfile(),
                            designer.getPosition().getName(),
                            distance, // ✅ 올바른 distance 매핑
                            designer.getExpertField().toString(),
                            designer.getIntroduce(),
                            designer.getPortfolios(),
                            designer.getType().toString(),
                            designer.getOfflinePrice(),
                            designer.getOnlinePrice()
                    );
                })
                .collect(Collectors.toList());
    }
}
