package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.CreateConsultingService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;
import blaybus.domain.time.application.service.impl.TimeServiceImpl;
import blaybus.domain.time.domain.entity.Time;
import blaybus.domain.time.domain.repository.BlaybusTimeRepository;
import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateConsultingServiceImpl implements CreateConsultingService {

    private final ConsultingRepository consultingRepository;
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;
    private final BlaybusTimeRepository timeRepository;

    @Override
    public ConsultingResponseDTO execute(ConsultingRequestDTO req, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Designer designer = designerRepository.findById(req.designerId())
                .orElseThrow(() -> new EntityNotFoundException("Designer not found: " + req.designerId()));


        // 구글 미트


        // 생성
        Consulting consulting = Consulting.builder()
                .user(user)
                .designer(designer)
                .meeting(meeting)
                .type(req.meet())  // 적절한 타입으로 변경
                .status(req.status()) // 초기 상태 지정
                .build();

        consultingRepository.save(consulting);

        // 예약 테이블 생성
        Time time = Time.createTime(consulting, designer, user, req.startTime());
        timeRepository.save(time);

        return new ConsultingResponseDTO(
                consulting.getId(),
                consulting.getUser().getId(),
                consulting.getDesigner().getId(),
                consulting.getMeeting(),
                consulting.getType(),
                consulting.getStatus()
        );
    }
}