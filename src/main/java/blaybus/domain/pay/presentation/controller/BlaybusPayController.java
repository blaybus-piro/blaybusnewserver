package blaybus.domain.pay.presentation.controller;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.presentation.dto.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
@Slf4j
public class BlaybusPayController {

    private final BlaybusPayService blaybusPayService;

    /**
     * [GET] 결제 준비
     * 예시: /api/pay/ready 유저는 @Auth~~ 받으면 됨
     */
    @GetMapping("/ready")
    public ResponseEntity<KakaoPayReadyResponse> payReady(
           @AuthenticationPrincipal String userId, // Oauth로부터 받기 근데 이부분이 필요할까 QR코드에서 인증하는대..
            HttpSession session
    ) {
        // 주문 번호 랜덤 생성
        String orderId = UUID.randomUUID().toString();
        // 결제 금액
        int amount = 2000000;

        KakaoPayReadyResponse response = blaybusPayService.payReady(orderId, userId, amount);
        log.info(userId);
        session.setAttribute("tid_" + orderId, response.getTid());
        String tid = (String) session.getAttribute("tid_" + orderId);
        log.info(tid);
        return ResponseEntity.ok(response);
    }

    /**
     * [GET] 결제 승인
     * 예시: /api/pay/approve?orderId=123&tid=xxx&pgToken=yyy
     * - 실제로는 approval_url로 리다이렉트되며 pg_token이 넘어올 때 서버가 받는 로직이 필요
     */
    @GetMapping("/approve")
    public ResponseEntity<?> payApprove(
            @RequestParam String orderId,
           @AuthenticationPrincipal String userId,
            @RequestParam("pg_token") String pgToken,
            HttpSession session
    ) {

        try {
            // 세션에서 tid 값 불러오기
            String tid = (String) session.getAttribute("tid_" + orderId);

            if (tid == null) {
                return ResponseEntity.badRequest().body(null); // 오류 처리
            }
            KakaoPayApproveResponse response = blaybusPayService.payApprove(orderId, userId, tid, pgToken);

            session.removeAttribute("tid_" + orderId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.info(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("결제 실패..");
        }

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