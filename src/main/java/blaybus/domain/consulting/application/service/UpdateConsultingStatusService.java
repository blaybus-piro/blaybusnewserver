package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.ConsultingStatus;

public interface UpdateConsultingStatusService {
    void updateConsultingStatus(long id, ConsultingStatus newStatus);
}
