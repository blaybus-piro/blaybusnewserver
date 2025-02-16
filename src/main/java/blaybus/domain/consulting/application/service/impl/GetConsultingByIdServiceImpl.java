package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.GetConsultingByIdService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetConsultingByIdServiceImpl implements GetConsultingByIdService {

    private final ConsultingRepository consultingRepository;

    @Override
    public Consulting getConsultingDetails(long id) {
        return consultingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulting with ID " + id + " not found"));
    }
}

