package blaybus.domain.pay.presentation.controller;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.presentation.dto.*;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class BlaybusPayController {

    private final BlaybusPayService blaybusPayService;

    /**
     * [GET] 결제 준비
     *  예시: /api/pay/ready?orderId=1231&amount=1000
     */
    @GetMapping("/ready")
    public ResponseEntity<KakaoPayReadyResponse> payReady(
            @RequestParam String orderId, // 백에서 해줘야될까??? 프에서 해줘야 될까??
            @RequestParam String userId,
            @RequestParam int amount
    ) {
        KakaoPayReadyResponse response = blaybusPayService.payReady(orderId, userId, amount);
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] 결제 승인
     *  예시: /api/pay/approve?orderId=123&tid=xxx&pgToken=yyy
     *  - 실제로는 approval_url로 리다이렉트되며 pg_token이 넘어올 때 서버가 받는 로직이 필요
     */
    @GetMapping("/approve")
    public ResponseEntity<KakaoPayApproveResponse> payApprove(
            @RequestParam String orderId,
            @AuthenticationPrincipal String userId,
            @RequestParam String tid,
            @RequestParam("pgToken") String pgToken
    ) {
        KakaoPayApproveResponse response = blaybusPayService.payApprove(orderId, userId, tid, pgToken);
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] 결제 단건 조회
     *  예시: /api/pay/order?tid=xxx
     */
    @GetMapping("/order")
    public ResponseEntity<KakaoPayOrderResponse> getOrder(@RequestParam String tid) {
        KakaoPayOrderResponse orderResponse = blaybusPayService.getOrder(tid);
        return ResponseEntity.ok(orderResponse);
    }
}