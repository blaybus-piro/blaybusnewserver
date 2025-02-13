package blaybus.domain.pay.infra.feignclient;

import blaybus.domain.pay.presentation.dto.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayReadyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "kakaoPayClient", url = "https://kapi.kakao.com")
public interface KakaoPayClient {

    /**
     * 1) 결제 준비 (ready)
     *    - POST /v1/payment/ready
     *    - Content-Type: application/x-www-form-urlencoded
     */
    @PostMapping(value = "/v1/payment/ready",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoPayReadyResponse ready(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Content-Type") String contentType,
            @RequestParam("cid") String cid,
            @RequestParam("partner_order_id") String partnerOrderId,
            @RequestParam("partner_user_id") String partnerUserId,
            @RequestParam("item_name") String itemName,
            @RequestParam("quantity") int quantity,
            @RequestParam("total_amount") int totalAmount,
            @RequestParam("vat_amount") int vatAmount,
            @RequestParam("tax_free_amount") int taxFreeAmount,
            @RequestParam("approval_url") String approvalUrl,
            @RequestParam("cancel_url") String cancelUrl,
            @RequestParam("fail_url") String failUrl
    );

    /**
     * 2) 결제 승인 (approve)
     *    - POST /v1/payment/approve
     */
    @PostMapping(value = "/v1/payment/approve",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoPayApproveResponse approve(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Content-Type") String contentType,
            @RequestParam("cid") String cid,
            @RequestParam("tid") String tid,
            @RequestParam("partner_order_id") String partnerOrderId,
            @RequestParam("partner_user_id") String partnerUserId,
            @RequestParam("pg_token") String pgToken
    );

    /**
     * 3) 결제 단건 조회 (order)
     *    - 카카오페이 문서에 따라 POST 방식일 수 있으므로 여기서는 POST로 설정
     */
    @PostMapping(value = "/v1/payment/order",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoPayOrderResponse getOrder(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("cid") String cid,
            @RequestParam("tid") String tid
    );
}
