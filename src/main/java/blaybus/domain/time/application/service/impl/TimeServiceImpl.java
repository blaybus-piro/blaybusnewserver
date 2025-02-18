package blaybus.domain.time.application.service.impl;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.time.application.service.TimeService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TimeServiceImpl implements TimeService {

    private final ConsultingRepository consultingRepository;

    @Override
    public List<String> getTime(){
        List<String> time= new ArrayList<>();
        List<Consulting> all = consultingRepository.findAll();

        for (Consulting consulting : all) {
            if (!(consulting.getStatus() == ConsultingStatus.CANCELED)) {
                time.add(consulting.getStartTime());
            }

        }

        return time;
    }


}
