//package blaybus.domain.designer.presentation.controller;
//
//import blaybus.domain.designer.domain.entity.Designer;
//import blaybus.domain.designer.application.service.GetDesignerByDistanceService;
//import blaybus.domain.designer.domain.entity.Type;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/designers")
//@RequiredArgsConstructor
//public class GetDesignerAllByDistanceController {
//
//    private final GetDesignerByDistanceService getDesignerByDistanceService;
//
//    @GetMapping("/by-location")
//    public ResponseEntity<List<Designer>> getDesignersByLocation(
//            @RequestParam("lat") double lat,
//            @RequestParam("lng") double lng,
//            @RequestParam("type") Type type) {
//        List<Designer> designers = getDesignerByDistanceService.getDesignersByLocation(lat, lng, type);
//        return ResponseEntity.ok(designers);
//    }
//
//}