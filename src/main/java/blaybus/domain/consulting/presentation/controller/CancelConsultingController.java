package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.application.service.UpdateConsultingStatusService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class CancelConsultingController {

    private final UpdateConsultingStatusService consultingService;

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelConsulting(@PathVariable long id) {
        consultingService.updateConsultingStatus(id, ConsultingStatus.CANCELED);
        return ResponseEntity.ok().build(); // 200 OK 반환
    }
}