package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.presentation.dto.request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;

public interface CreateConsultingService {
    ConsultingResponseDTO execute(ConsultingRequestDTO req, String userId);
}

