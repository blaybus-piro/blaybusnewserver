package blaybus.domain.designer.presentation.controller;

import blaybus.domain.designer.application.service.GetDesignerByDistanceService;
import blaybus.domain.designer.domain.entity.Type;
import blaybus.domain.designer.presentation.dto.response.DesignerDistanceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designers")
@RequiredArgsConstructor
public class GetDesignerAllByDistanceController {

    private final GetDesignerByDistanceService getDesignerByDistanceService;

    @GetMapping("/nearby")
    public List<DesignerDistanceResponseDTO> getNearbyDesigners(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam Type type) {
        return getDesignerByDistanceService.getDesignersByLocation(lat, lng, type);
    }
}
