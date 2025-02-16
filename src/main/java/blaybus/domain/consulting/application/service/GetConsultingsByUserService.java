package blaybus.domain.consulting.application.service;

import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.Response.ConsultingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConsultingsByUserService {

    private final ConsultingRepository consultingRepository;

    public List<ConsultingResponseDTO> execute(String userId) {
        return consultingRepository.findByUserId(userId)
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

