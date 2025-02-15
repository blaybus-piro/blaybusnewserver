package blaybus.domain.meeting.presentation.controller;

import blaybus.domain.meeting.application.service.MeetingService;
import blaybus.domain.meeting.presentation.dto.request.MeetingCreateRequest;
import blaybus.domain.meeting.presentation.dto.response.MeetingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {
    private final MeetingService meetingService;


    @PostMapping
    public ResponseEntity<MeetingResponse> createMeeting(
            @AuthenticationPrincipal String userId,
            @RequestBody MeetingCreateRequest request) {
        // 링크 생성 및 title 받기
        MeetingResponse meetingResponse = meetingService.createMeeting(userId, request);

        return ResponseEntity.status(201).body(meetingResponse);
    }
}