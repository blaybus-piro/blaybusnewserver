package blaybus.domain.pay.presentation.controller;

import blaybus.domain.pay.application.repository.BlaybusPayRepository;
import blaybus.domain.pay.domain.BlaybusPayTid;
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
    private final BlaybusPayRepository blaybusPayRepository;

    /**
     * [GET] 결제 준비
     * /api/pay/ready 유저는 @Auth~~ 받으면 됨
     */
    @PostMapping("/ready")
    public ResponseEntity<KakaoPayReadyResponse> payReady(
            @RequestBody ReadyRequestDTO readyRequestDTO, // 여러개 있을때 @RequestBody로 객체로 받으삼!
            @AuthenticationPrincipal String userId, // Oauth로부터 받기 근데 이부분이 필요할까 QR코드에서 인증하는대..
            HttpSession session
    ) {

        if (userId == null) {
            throw new IllegalStateException("유저 아이디가 존재하지 않습니다.");
        }
        // 주문 번호 랜덤 생성
        String orderId = UUID.randomUUID().toString();

        KakaoPayReadyResponse response = blaybusPayService.payReady(orderId, userId, readyRequestDTO.getAmount());

        BlaybusPayTid blaybusPayTid = BlaybusPayTid.builder()
                .id(orderId)
                .tid(response.getTid())
                .build();
        blaybusPayRepository.save(blaybusPayTid);

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
            @AuthenticationPrincipal String userId, // Oauth로부터 받기 근데 이부분이 필요할까 QR코드에서 인증하는대..
            HttpSession session
    ) {
        try {


            // 값 불러오기
            Optional<BlaybusPayTid> findPayTid = blaybusPayRepository.findById(orderId);
            BlaybusPayTid blaybusPayTid = findPayTid.get();
            if (blaybusPayTid.getTid() == null) {
                throw new BlaybusPayException(HttpStatus.PAYMENT_REQUIRED, "TID 값이 존재하지 않습니다.");
            }

            KakaoPayApproveResponse response = blaybusPayService.payApprove(orderId, userId, blaybusPayTid.getTid(), pgToken);

            // 값 삭제
            blaybusPayRepository.delete(blaybusPayTid);
            return ResponseEntity.ok(response);
        } catch (BlaybusPayException e) {
            log.error("결제 오류 발생: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("결제 승인 중 알 수 없는 오류 발생", e);
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