package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.UpdateConsultingStatusService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateConsultingStatusServiceImpl implements UpdateConsultingStatusService {

    private final ConsultingRepository consultingRepository;

    @Override
    public void updateConsultingStatus(Long id, ConsultingStatus newStatus) {
        Consulting consulting = consultingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulting with ID " + id + " not found"));

        consulting.updateStatus(newStatus); // 변경 사항 바꿈
    }
}
