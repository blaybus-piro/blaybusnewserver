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

import java.util.List;

@RestController
//@RequestMapping("/api/time") 안쓰는 API 입니다.
@RequiredArgsConstructor
@Slf4j
public class BlaybusTimeController {


    private final TimeService blaybusTimeService;


    // 예약 조회
    //@PostMapping
    public ResponseEntity<List<TimeResponse>> getTime(
            @AuthenticationPrincipal String userId
    ) {
        List<TimeResponse> response = blaybusTimeService.findTime(userId);
        return ResponseEntity.ok(response);
    }


    // 예약 상세 조회
    //@PostMapping("/detail")
    public ResponseEntity<DetailTimeResponse> detailTime(
            @RequestBody DetailRequestDTO req
    ) {
        DetailTimeResponse response = blaybusTimeService.findDetail(req);
        return ResponseEntity.ok(response);
    }
}

