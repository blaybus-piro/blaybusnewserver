package blaybus.domain.designer.presentation.controller;

import blaybus.domain.designer.application.service.GetConsultingDesignersService;
import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designers/online")
@RequiredArgsConstructor
public class GetOnlineConsultingDesignersController {

    private final GetConsultingDesignersService getOnlineConsultingDesignersService;

    @GetMapping
    public ResponseEntity<List<DesignerResponseDTO>> getOnlineDesigners(@RequestParam(defaultValue = "ASC") String sortOrder) {
        return ResponseEntity.ok(getOnlineConsultingDesignersService.execute(sortOrder));
    }
}