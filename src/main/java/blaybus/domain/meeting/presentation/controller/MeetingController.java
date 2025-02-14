//package blaybus.domain.meeting.presentation.controller;
//
//import blaybus.domain.meeting.application.service.MeetingService;
//import blaybus.domain.meeting.entity.Meeting;
//import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
//import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
//import blaybus.domain.meeting.repository.MeetingRepository;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/meetings")
//@RequiredArgsConstructor
//public class MeetingController {
//    private final MeetingService meetingService;
//    private final MeetingRepository meetingRepository;
//
//    @PostMapping
//    public MeetingResponse createMeeting(@RequestBody @Valid MeetingCreateRequest request) {
//        // 링크 생성 및 title 받기
//        MeetingResponse meetingResponse = meetingService.createMeeting(request);
//
//        Meeting meeting = Meeting.builder()
//                .title(meetingResponse.title())  // 서비스에서 생성된 title 사용
//                .meetUrl(meetingResponse.hangoutLink())
//                .startTime(request.startTime())
//                .endTime(request.endTime())
//                .build();
//        meetingRepository.save(meeting);
//
//        return meetingResponse;
//    }
//}