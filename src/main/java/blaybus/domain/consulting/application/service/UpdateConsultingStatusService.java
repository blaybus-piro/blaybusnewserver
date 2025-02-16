package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;

public interface UpdateConsultingStatusService {
    Consulting updateConsultingStatus(long id, ConsultingStatus newStatus);
}
