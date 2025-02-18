package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.presentation.dto.request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingUserResponse;

import java.util.List;

public interface CreateConsultingService {
    ConsultingResponseDTO execute(ConsultingRequestDTO req, String userId);

 List<ConsultingUserResponse> getConsulting(String userId);

}

