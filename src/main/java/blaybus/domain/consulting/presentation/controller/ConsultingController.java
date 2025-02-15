package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.application.service.ConsultingService;
import blaybus.domain.consulting.presentation.DTO.ConsultingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultings")
@RequiredArgsConstructor
public class ConsultingController {

    private final ConsultingService consultingService;

    // **User ID로 상담 조회**
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ConsultingDTO>> getConsultingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(consultingService.getConsultingsByUserId(userId));
    }

    // **상담 완료 처리 (SCHEDULED → COMPLETE)**
    @PatchMapping("/{consultingId}/complete")
    public ResponseEntity<ConsultingDTO> completeConsulting(@PathVariable int consultingId) {
        return ResponseEntity.ok(consultingService.completeConsulting(consultingId));
    }

    // **상담 취소 처리 (SCHEDULED → CANCELED)**
    @PatchMapping("/{consultingId}/cancel")
    public ResponseEntity<ConsultingDTO> cancelConsulting(@PathVariable int consultingId) {
        return ResponseEntity.ok(consultingService.cancelConsulting(consultingId));
    }
}
