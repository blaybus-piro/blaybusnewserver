package blaybus.domain.pay.application.service.impl;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.infra.feignclient.KakaoPayClient;
import blaybus.domain.pay.presentation.dto.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayReadyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlaybusPayServiceImpl implements BlaybusPayService {

    private final KakaoPayClient kakaoPayClient;

    @Value("${kakao.pay.admin-key}")
    private String adminKey;  // 카카오페이 관리자 키

    @Value("${kakao.pay.cid}")
    private String cid;       // 가맹점 CID 테스트

    /**
     * 1) 결제 준비
     *    - 결제 요청에 필요한 파라미터를 세팅하고, KakaoPayClient.ready() 호출
     */
    @Override
    public KakaoPayReadyResponse payReady(String orderId, String userId, int amount) {
        String authorization = "KakaoAK " + adminKey;
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        log.info(adminKey);
        log.info(cid);
        // 테스트용 파라미터 (실제 구현 시 조정이 필요함.... 이건 예시)
        String itemName = "디자이너와의 면담";
        int quantity = 1;
        int vatAmount = 0;
        int taxFreeAmount = 0;

        // 실제 운영 서버로 변경해야됨 성공 리다이렉트 url
        String approvalUrl = "http://localhost:8080/api/pay/approve?orderId=" + orderId;

        // 만들어야될까낭...
        String cancelUrl   = "http://localhost:8080/api/pay/cancel?orderId=" + orderId;
        String failUrl     = "http://localhost:8080/api/pay/fail?orderId=" + orderId;

        // FeignClient 호출
        return kakaoPayClient.ready(
                authorization,
                contentType,
                cid,
                orderId,
                userId,
                itemName,
                quantity,
                amount,
                vatAmount,
                taxFreeAmount,
                approvalUrl,
                cancelUrl,
                failUrl
        );
    }

    /**
     * 2) 결제 승인
     *    - 사용자 결제 완료 후 pg_token을 받아 최종 승인
     */
    @Override
    public KakaoPayApproveResponse payApprove(String orderId, String userId, String tid, String pgToken) {
        String authorization = "KakaoAK " + adminKey;
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        return kakaoPayClient.approve(
                authorization,
                contentType,
                cid,
                tid,
                orderId,
                userId,
                pgToken
        );
    }

    /**
     * 3) 단건 조회
     *    - tid로 결제 정보를 조회
     */
    @Override
    public KakaoPayOrderResponse getOrder(String tid) {
        String authorization = "KakaoAK " + adminKey;
        return kakaoPayClient.getOrder(authorization, cid, tid);
    }
}