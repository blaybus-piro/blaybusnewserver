package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class UserConsultingController {

    private final ConsultingRepository consultingRepository;

    // 특정 유저의 상담 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Consulting>> getUserConsultings(@PathVariable String userId) {
        List<Consulting> consultings = consultingRepository.findAllByUserId(userId);
        return ResponseEntity.ok(consultings);
    }
}
