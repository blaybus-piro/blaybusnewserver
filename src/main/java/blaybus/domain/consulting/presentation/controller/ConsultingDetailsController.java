package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.application.service.GetConsultingByIdService;
import blaybus.domain.consulting.domain.entity.Consulting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class ConsultingDetailsController {

    private final GetConsultingByIdService getConsultingByIdService;

    // ID를 통해 상담 세부사항 조회
    @GetMapping("/{id}")
    public ResponseEntity<Consulting> getConsultingDetails(@PathVariable long id) {
        Consulting consulting = getConsultingByIdService.getConsultingDetails(id);
        return ResponseEntity.ok(consulting);
    }
}