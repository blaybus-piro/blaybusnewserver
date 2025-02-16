package blaybus.domain.consulting.application.service;

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
public class UpdateConsultingStatusService {

    private final ConsultingRepository consultingRepository;

    // 상담 상태 업데이트 메서드
    @Transactional
    public Consulting updateConsultingStatus(long id, ConsultingStatus newStatus) {
        // ID로 상담을 조회 (존재하지 않으면 예외 발생)
        Optional<Consulting> consultingOptional = consultingRepository.findById(id).map(obj -> (Consulting) obj);
        if (consultingOptional.isEmpty()) {
            throw new EntityNotFoundException("Consulting with ID " + id + " not found");
        }
        Consulting consulting = consultingOptional.get();

        // 상태 변경
        consulting.updateStatus(newStatus);

        // 변경된 상태 저장
        return consultingRepository.save(consulting);
    }
}