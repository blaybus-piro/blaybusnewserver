package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.GetConsultingsByUserService;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConsultingsByUserServiceImpl implements GetConsultingsByUserService {

    private final ConsultingRepository consultingRepository;

    @Override
    public List<ConsultingResponseDTO> execute(String userId) {
        return consultingRepository.findAllByUserId(userId)
                .stream()
                .map(consulting -> new ConsultingResponseDTO(
                        consulting.getId(),
                        consulting.getUser().getId(),
                        consulting.getDesigner().getId(),
                        consulting.getMeeting(),
                        consulting.getType(),
                        consulting.getStatus()
                ))
                .toList();
    }
}
