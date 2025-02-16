package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetConsultingByIdService {
    private final ConsultingRepository consultingRepository;

    public Consulting getConsultingDetails(long id) {
        return consultingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulting with ID " + id + " not found"));
    }
}
