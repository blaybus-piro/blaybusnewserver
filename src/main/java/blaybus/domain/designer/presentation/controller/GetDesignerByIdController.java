package blaybus.domain.designer.presentation.controller;

import blaybus.domain.designer.presentation.dto.response.DesignerResponseDTO;
import blaybus.domain.designer.application.service.GetDesignerByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/designers")
@RequiredArgsConstructor
public class GetDesignerByIdController {

    private final GetDesignerByIdService getDesignerByIdService;

    @GetMapping("/{id}")
    public ResponseEntity<DesignerResponseDTO> getDesignerById(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity.ok(getDesignerByIdService.execute(id));
    }
}