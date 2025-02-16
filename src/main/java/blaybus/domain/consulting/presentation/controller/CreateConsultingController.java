package blaybus.domain.consulting.presentation.controller;
import blaybus.domain.consulting.application.service.CreateConsultingService;
import blaybus.domain.consulting.presentation.dto.request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/consulting/create")
@RequiredArgsConstructor
public class CreateConsultingController {

    private final CreateConsultingService createConsultingService;

    @PostMapping
    public ResponseEntity<ConsultingResponseDTO> createConsulting(@AuthenticationPrincipal String userId,
                                                                      @RequestBody ConsultingRequestDTO requestDTO) {
        ConsultingResponseDTO responseDTO = createConsultingService.execute(requestDTO, userId);
        return ResponseEntity.ok(responseDTO);
    }
}