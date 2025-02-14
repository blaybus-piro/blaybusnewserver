package blaybus.domain.metting.presentation.controller;

import blaybus.domain.metting.application.service.BlayBusMeetService;
import blaybus.domain.metting.application.service.impl.BlayBusMeetServiceImpl;
import blaybus.domain.metting.presentation.dto.CreateMeetRequest;
import blaybus.domain.metting.presentation.dto.CreateMeetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meet")
public class BlayBusMeetController {

    private final BlayBusMeetServiceImpl blayBusMeetService;

    @PostMapping
    public CreateMeetResponse createMeet(@RequestBody CreateMeetRequest request) {
        return blayBusMeetService.createMeet(request);
    }
}
