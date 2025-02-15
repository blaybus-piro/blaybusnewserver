package blaybus.domain.pay.presentation.controller;

import blaybus.domain.pay.domain.repository.BlaybusPayRepository;
import blaybus.domain.pay.domain.entity.BlaybusPayTid;
import blaybus.domain.pay.infra.exception.BlaybusPayException;
import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.presentation.dto.ReadyRequest.ReadyRequestDTO;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayReadyResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
@Slf4j
public class BlaybusPayController {

    private final BlaybusPayService blaybusPayService;

    /**
     * [GET] 결제 준비
     * /api/pay/ready 유저는 @Auth~~ 받으면 됨
     */
    @PostMapping("/ready")
    public ResponseEntity<KakaoPayReadyResponse> payReady(
            @RequestBody ReadyRequestDTO readyRequestDTO,
            @AuthenticationPrincipal String userId
    ) {
        String orderId = blaybusPayService.randomOrderId();
        KakaoPayReadyResponse response = blaybusPayService.payReady(userId, orderId, readyRequestDTO.getAmount());
        blaybusPayService.save(response, orderId);
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] 결제 승인
     * /api/pay/approve?orderId=123&tid=xxx&pgToken=yyy
     * - 실제로는 approval_url로 리다이렉트되며 pg_token이 넘어올 때 서버가 받는 로직이 필요
     */
    @GetMapping("/approve")
    public ResponseEntity<?> payApprove(
            @RequestParam String orderId,
            @RequestParam("pg_token") String pgToken,
            @AuthenticationPrincipal String userId
    ) {
        KakaoPayApproveResponse response = blaybusPayService.payApprove(orderId, userId, pgToken);
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] 결제 단건 조회 그냥 넣어봤음 블로그에 있길래..
     * 예시: /api/pay/order?tid=xxx
     */
    @GetMapping("/order")
    public ResponseEntity<KakaoPayOrderResponse> getOrder(@RequestParam String tid) {
        KakaoPayOrderResponse orderResponse = blaybusPayService.getOrder(tid);
        return ResponseEntity.ok(orderResponse);
    }
}