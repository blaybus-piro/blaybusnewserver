package blaybus.domain.consulting.application.service.impl;

import blaybus.domain.consulting.application.service.CreateConsultingService;
import blaybus.domain.consulting.domain.entity.Consulting;
import blaybus.domain.consulting.domain.entity.ConsultingStatus;
import blaybus.domain.consulting.domain.entity.ConsultingType;
import blaybus.domain.consulting.domain.repository.ConsultingRepository;
import blaybus.domain.consulting.presentation.dto.request.ConsultingRequestDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingResponseDTO;
import blaybus.domain.consulting.presentation.dto.response.ConsultingUserResponse;
import blaybus.domain.meeting.application.service.impl.MeetingServiceImpl;
import blaybus.domain.meeting.domain.entity.Meeting;
import blaybus.domain.meeting.domain.repository.MeetingRepository;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;


import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.domain.designer.domain.entity.Designer;
import blaybus.domain.designer.domain.repository.DesignerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CreateConsultingServiceImpl implements CreateConsultingService {

    private final ConsultingRepository consultingRepository;
    private final UserRepository userRepository;
    private final DesignerRepository designerRepository;

    private final MeetingServiceImpl meetingService;
    private final MeetingRepository meetingRepository;

    @Override
    public ConsultingResponseDTO execute(ConsultingRequestDTO req, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));

        Designer designer = designerRepository.findById(req.designerId())
                .orElseThrow(() -> new EntityNotFoundException("Designer not found: " + req.designerId()));


        log.info(userId);

        Meeting findMeeting;

        if (ConsultingType.ONLINE.equals(ConsultingType.valueOf(req.meet().toUpperCase()))) {
            // 구글 미트 생성
            MeetingResponse meeting = meetingService.createMeeting(userId, req.startTime(), designer);
            findMeeting = meetingRepository.findById(meeting.id()).orElse(null);
        } else {
            Long offlineId = meetingService.save();
            findMeeting = meetingRepository.findById(offlineId).orElse(null);
        }

        String status = "";
        if (req.pay().equals("카카오페이")) {
            status = "예약 완료";
        } else {
            status = "예약 대기";
        }

        log.info("Received meet type: '{}'", req.meet());
        ConsultingType type = ConsultingType.valueOf(req.meet().trim().toUpperCase());

        // 컨설팅 생성
        Consulting consulting = Consulting.builder()
                .user(user)
                .designer(designer)
                .meeting(findMeeting)
                .pay(req.pay())
                .type(type)  // 적절한 타입으로 변경
                .status(ConsultingStatus.fromString(status)) // 초기 상태 지정
                .startTime(req.startTime())
                .build();

        consultingRepository.save(consulting);
        log.info("Received meet type2222: '{}'", req.meet());
        return new ConsultingResponseDTO(
                consulting.getId(),
                consulting.getUser().getId(),
                consulting.getDesigner().getId(),
                consulting.getMeeting(),
                type,
                ConsultingStatus.fromString(status),
                consulting.getPay(),
                consulting.getStartTime()
        );
    }

    @Override
    public List<ConsultingUserResponse> getConsulting(String userId) {
        List<Consulting> consultings = consultingRepository.findAllByUser_Id(userId);

        List<ConsultingUserResponse> responses = new ArrayList<>();
        for (Consulting consulting : consultings) {
            String money = "";
            if (consulting.getType() == ConsultingType.ONLINE) {
                money = String.valueOf(consulting.getDesigner().getOnlinePrice());
            } else {
                money = String.valueOf(consulting.getDesigner().getOfflinePrice());
            }
            responses.add(ConsultingUserResponse.of(
                    consulting.getId(), consulting.getDesigner().getId(), consulting.getDesigner().getName(), consulting.getDesigner().getProfile(), consulting.getPay(), consulting.getType().toString(),
                    consulting.getStatus().toString(), consulting.getMeeting().getMeetUrl(), consulting.getStartTime(), money)
            );
        }

        return responses;
    }
}