package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.DTO.ConsultingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsultingService {

    private final ConsultingRepository consultingRepository;

    // **User ID로 상담 조회**
    public List<ConsultingDTO> getConsultingsByUserId(String userId) {
        return consultingRepository.findByUser_Id(userId) // 수정된 메서드 호출
                .stream()
                .map(ConsultingDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // **SCHEDULED → COMPLETE 상태 변경**
    @Transactional
    public ConsultingDTO completeConsulting(int consultingId) {
        Consulting consulting = consultingRepository.findById(consultingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상담을 찾을 수 없습니다: " + consultingId));

        if (consulting.getStatus() != Consulting.ConsultingStatus.SCHEDULED) {
            throw new IllegalStateException("현재 상태가 SCHEDULED가 아닙니다. 완료 처리할 수 없습니다.");
        }

        consulting = Consulting.builder()
                .id(consulting.getId())
                .user(consulting.getUser())
                .designer(consulting.getDesigner())
                .position(consulting.getPosition())
                .meetUrl(consulting.getMeetUrl())
                .type(consulting.getType())
                .status(Consulting.ConsultingStatus.COMPLETE)
                .build();

        consultingRepository.save(consulting);
        return ConsultingDTO.fromEntity(consulting);
    }

    // **SCHEDULED → CANCELED 상태 변경**
    @Transactional
    public ConsultingDTO cancelConsulting(int consultingId) {
        Consulting consulting = consultingRepository.findById(consultingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상담을 찾을 수 없습니다: " + consultingId));

        if (consulting.getStatus() != Consulting.ConsultingStatus.SCHEDULED) {
            throw new IllegalStateException("현재 상태가 SCHEDULED가 아닙니다. 취소할 수 없습니다.");
        }

        consulting = Consulting.builder()
                .id(consulting.getId())
                .user(consulting.getUser())
                .designer(consulting.getDesigner())
                .position(consulting.getPosition())
                .meetUrl(consulting.getMeetUrl())
                .type(consulting.getType())
                .status(Consulting.ConsultingStatus.CANCELED)
                .build();

        consultingRepository.save(consulting);
        return ConsultingDTO.fromEntity(consulting);
    }
}
