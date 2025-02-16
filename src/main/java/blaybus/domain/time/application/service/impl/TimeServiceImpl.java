package blaybus.domain.time.application.service.impl;

import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.time.application.service.TimeService;
import blaybus.domain.time.domain.entity.Time;
import blaybus.domain.time.domain.repository.BlaybusTimeRepository;
import blaybus.domain.time.presentation.dto.req.DetailRequestDTO;
import blaybus.domain.time.presentation.dto.res.detailtime.DetailTimeResponse;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.ConsultingDetail;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.DesignerDetail;
import blaybus.domain.time.presentation.dto.res.detailtime.detail.UserDetail;
import blaybus.domain.time.presentation.dto.res.listtime.TimeResponse;
import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.DesignerView;
import blaybus.domain.time.presentation.dto.res.listtime.timeconsulting.TimeConsulting;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TimeServiceImpl implements TimeService {

    private final BlaybusTimeRepository blaybusTimeRepository;

    private final ConsultingRepository consultingRepository;



    @Override
    public List<TimeResponse> findTime(String userId) {

        List<TimeResponse> timeResponses = new ArrayList<>();

        List<Time> times = blaybusTimeRepository.findByUserId(userId);

        for (Time time : times) {
            String googleLink = time.getConsulting().getMeeting().getMeetUrl();

            Designer designer = time.getDesigner();
            DesignerView designerView = new DesignerView();
            designerView.setName(designer.getName());
            designerView.setProfile(designer.getProfile());

            Consulting consulting = time.getConsulting();
            TimeConsulting timeConsulting = new TimeConsulting();
            // 시간 나중에 해야됌
            // ???
            timeConsulting.setId(consulting.getId());
            timeConsulting.setMeet(consulting.getType().toString());
            timeConsulting.setStatus(consulting.getStatus().toString());

            TimeResponse timeResponse =
                    TimeResponse.of(timeConsulting, googleLink, designerView);

            timeResponses.add(timeResponse);
        }

        return timeResponses;
    }


    @Override
    public DetailTimeResponse findDetail(DetailRequestDTO req) {

        ConsultingDetail consultingDetail = new ConsultingDetail();
        Long consultingId = req.id();
        Optional<Consulting> findConsulting = consultingRepository.findById(consultingId);
        Consulting consulting = findConsulting.get();
        // 시간도 줘야됨
        consultingDetail.setMeet(String.valueOf(consulting.getType()));
        consultingDetail.setStatus(String.valueOf(consulting.getStatus()));

        DesignerDetail designerDetail = new DesignerDetail();
        designerDetail.setName(consulting.getDesigner().getName());
        designerDetail.setProfile(consulting.getDesigner().getProfile());

        UserDetail userDetail = new UserDetail();
        userDetail.setName(consulting.getUser().getName());
        userDetail.setEmail(consulting.getUser().getMail());


        DetailTimeResponse detailTimeResponse = DetailTimeResponse.of(userDetail, designerDetail, consultingDetail, consulting.getMeeting().getMeetUrl());
        return detailTimeResponse;
    }
}
