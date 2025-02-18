package blaybus.domain.consulting.presentation.controller;

import blaybus.domain.consulting.application.service.CreateConsultingService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.response.ConsultingUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulting")
@RequiredArgsConstructor
public class UserConsultingController {

    private final CreateConsultingService createConsultingService;


    // 특정 유저의 상담 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ConsultingUserResponse>> getUserConsultings(@PathVariable String userId) {
        List<ConsultingUserResponse> responses = createConsultingService.getConsulting(userId);
        return ResponseEntity.ok(responses);
    }
}
