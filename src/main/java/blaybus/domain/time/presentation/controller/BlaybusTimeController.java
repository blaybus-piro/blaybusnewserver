package blaybus.domain.time.presentation.controller;

import blaybus.domain.time.application.service.TimeService;
import blaybus.domain.time.presentation.dto.req.DetailRequestDTO;
import blaybus.domain.time.presentation.dto.res.detailtime.DetailTimeResponse;
import blaybus.domain.time.presentation.dto.res.listtime.TimeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/time")
@RequiredArgsConstructor
@Slf4j
public class BlaybusTimeController {


    private final TimeService blaybusTimeService;

    // 타임테이블 조회
    @GetMapping
    public ResponseEntity<List<LocalDateTime>> getTime(
            @RequestBody DetailRequestDTO detailRequestDTO
    ) {
        List<LocalDateTime> time = blaybusTimeService.getTime(detailRequestDTO);
        return ResponseEntity.ok(time);
    }

}

