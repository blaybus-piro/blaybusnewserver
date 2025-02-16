package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;

import java.util.List;

public interface GetConsultingsByUserService {
    List<ConsultingResponseDTO> execute(String userId);
}