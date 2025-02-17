//package blaybus.domain.designer.application.service.impl;
//
//import blaybus.domain.designer.domain.entity.Designer;
//import blaybus.domain.designer.domain.entity.Type;
//import blaybus.domain.designer.domain.repository.DesignerRepository;
//import blaybus.domain.map.application.service.PositionDistanceCalculateService;
//import blaybus.domain.designer.application.service.GetDesignerByDistanceService;
//import blaybus.domain.map.presentation.dto.response.PositionResponseDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class GetDesignerByDistanceServiceImpl implements GetDesignerByDistanceService {
//
//    private final DesignerRepository designerRepository;
//    private final PositionDistanceCalculateService positionDistanceCalculateService; // 거리 정렬 서비스 변경
//
//    @Override
//    public List<Designer> getDesignersByLocation(double lat, double lng, Type type) {
//        List<PositionResponseDTO> orderedNames = positionDistanceCalculateService.orderPositionByDistance(lat, lng); // 정렬된 name 리스트
//        if (orderedNames.isEmpty()) {
//            return List.of();
//        }
//        return designerRepository.findAllByPositionNameAndTypeInOrderByCustomOrder(orderedNames, type);
//    }
//}