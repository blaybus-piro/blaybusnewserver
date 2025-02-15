package blaybus.domain.designer.presentation.controller;

import blaybus.domain.designer.application.service.DesignerService;
import blaybus.domain.designer.presentation.DTO.DesignerDTO;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.consulting.domain.entity.Consulting.ConsultingType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/designers")
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;

    // **디자이너 단건 조회**
    @GetMapping("/{id}")
    public ResponseEntity<DesignerDTO> getDesigner(@PathVariable String id) {
        return ResponseEntity.ok(designerService.getDesignerById(id));
    }

    // **온라인 상담 가능 디자이너 목록 조회**
    @GetMapping("/consulting/online")
    public ResponseEntity<List<DesignerDTO>> getOnlineConsultingDesigners(
            @RequestParam ConsultingType consultingType,
            @RequestParam(defaultValue = "ASC") String sortOrder) {
        return ResponseEntity.ok(designerService.getOnlineConsultingDesigners(consultingType, sortOrder));
    }

    // **오프라인 상담 가능 디자이너 목록 조회**
    @GetMapping("/consulting/offline")
    public ResponseEntity<List<DesignerDTO>> getOfflineConsultingDesigners(
            @RequestParam ConsultingType consultingType,
            @RequestParam(defaultValue = "ASC") String sortOrder) {
        return ResponseEntity.ok(designerService.getOfflineConsultingDesigners(consultingType, sortOrder));
    }
}