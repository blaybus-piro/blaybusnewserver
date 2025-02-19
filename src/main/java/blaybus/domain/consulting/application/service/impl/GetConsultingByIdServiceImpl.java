package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.GetConsultingByIdService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.response.DetailConsultingResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetConsultingByIdServiceImpl implements GetConsultingByIdService {

    private final ConsultingRepository consultingRepository;

    @Override
    public DetailConsultingResponse getConsultingDetails(Long id) {
        Optional<Consulting> findConsulting = consultingRepository.findById(id);

        Consulting consulting = findConsulting.get();

        String money ="";
        if(consulting.getType() == ConsultingType.ONLINE){
            money= String.valueOf(consulting.getDesigner().getOnlinePrice());
        }else{
            money= String.valueOf(consulting.getDesigner().getOfflinePrice());
        }
        DetailConsultingResponse response = DetailConsultingResponse.of(consulting.getId(), consulting.getUser().getName(), consulting.getUser().getMail(),
                consulting.getDesigner().getId(), consulting.getDesigner().getName(), consulting.getDesigner().getProfile(),
                consulting.getPay(), consulting.getType().toString(), consulting.getStatus().toString(),
                consulting.getMeeting().getMeetUrl(), consulting.getStartTime(), money);

        return response;

    }
}