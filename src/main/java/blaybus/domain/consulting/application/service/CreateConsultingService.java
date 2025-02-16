package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.Request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.Response.ConsultingResponseDTO;
import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateConsultingService {

    private final ConsultingRepository consultingRepository;
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;

    public ConsultingResponseDTO execute(ConsultingRequestDTO requestDTO) {
        // 🔹 User 및 Designer 조회 후 객체 할당
        User user = userRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + requestDTO.userId()));

        Designer designer = designerRepository.findById(requestDTO.designerId())
                .orElseThrow(() -> new EntityNotFoundException("Designer not found: " + requestDTO.designerId()));

        // 🔹 Consulting 객체 생성 (User와 Designer를 객체로 할당)
        Consulting consulting = new Consulting(
                user,  // ✅ User 객체로 변환
                designer, // ✅ Designer 객체로 변환
                requestDTO.meeting(),
                requestDTO.type(),
                requestDTO.status()
        );

        consultingRepository.save(consulting);

        // 🔹 DTO 반환
        return new ConsultingResponseDTO(
                consulting.getId(),
                consulting.getUser().getId(),    // String으로 변환
                consulting.getDesigner().getId(), // String으로 변환
                consulting.getMeeting(),
                consulting.getType(),
                consulting.getStatus()
        );
    }
}
