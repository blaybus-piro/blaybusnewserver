package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.presentation.dto.response.DetailConsultingResponse;

public interface GetConsultingByIdService {
    DetailConsultingResponse getConsultingDetails(Long id);
}


