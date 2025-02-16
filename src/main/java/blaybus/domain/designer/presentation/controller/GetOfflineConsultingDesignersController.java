package blaybus.domain.designer.presentation.controller;

import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;
import blaybus.domain.designer.application.service.GetConsultingDesignersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designers/offline")
@RequiredArgsConstructor
public class GetOfflineConsultingDesignersController {

    private final GetConsultingDesignersService getOfflineConsultingDesignersService;

    @GetMapping
    public ResponseEntity<List<DesignerResponseDTO>> getOfflineDesigners(@RequestParam(defaultValue = "ASC") String sortOrder) {
        return ResponseEntity.ok(getOfflineConsultingDesignersService.execute(sortOrder));
    }
}