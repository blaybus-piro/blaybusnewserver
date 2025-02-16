package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulting")
@RequiredArgsConstructor
public class ConsultingDetailsController {

    private final ConsultingRepository consultingRepository;

    // ID를 통해 상담 세부사항 조회
    @GetMapping("/{id}")
    public ResponseEntity<Consulting> getConsultingDetails(@PathVariable long id) {
        Consulting consulting = consultingRepository.findById(id)
                .map(obj -> (Consulting) obj)
                .orElseThrow(() -> new EntityNotFoundException("Consulting with ID " + id + " not found"));
        return ResponseEntity.ok(consulting);
    }
}