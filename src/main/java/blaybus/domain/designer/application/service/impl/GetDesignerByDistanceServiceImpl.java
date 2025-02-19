package blaybus.domain.designer.application.service.impl;

import blaybus.domain.designer.application.service.GetDesignerByDistanceService;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import blaybus.domain.map.application.service.PositionDistanceCalculateService;
import blaybus.domain.map.presentation.dto.response.PositionResponseDTO;
import blaybus.domain.designer.presentation.dto.response.DesignerDistanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

        // ✅ DTO에서 Position의 name 값만 추출하여 리스트로 변환
        List<String> positionNames = positions.stream()
                .map(PositionResponseDTO::name)
                .collect(Collectors.toList());

        // ✅ Repository 호출 시 Position의 name 값을 기준으로 검색
        List<Designer> designers = designerRepository.findAllByPositionNames(positionNames);

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
                    designer.getPosition().getName(), // 🔹 올바른 필드 참조
                    distance,
                    designer.getExpertField().toString(),
                    designer.getIntroduce(),
                    designer.getPortfolios(),
                    designer.getType().toString(),
                    designer.getOfflinePrice(),
                    designer.getOnlinePrice()
            );
        }).sorted(Comparator.comparing(DesignerDistanceResponseDTO::distance)).collect(Collectors.toList());
    }
}
