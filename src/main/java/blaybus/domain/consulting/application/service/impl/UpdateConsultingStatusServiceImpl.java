package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.UpdateConsultingStatusService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateConsultingStatusServiceImpl implements UpdateConsultingStatusService {

    private final ConsultingRepository consultingRepository;

    @Override
    @Transactional
    public Consulting updateConsultingStatus(long id, ConsultingStatus newStatus) {
        Optional<Consulting> consultingOptional = consultingRepository.findById(id);
        if (consultingOptional.isEmpty()) {
            throw new EntityNotFoundException("Consulting with ID " + id + " not found");
        }
        Consulting consulting = consultingOptional.get();

        consulting.updateStatus(newStatus);

        return consultingRepository.save(consulting);
    }
}