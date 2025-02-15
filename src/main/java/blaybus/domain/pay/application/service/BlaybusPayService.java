package blaybus.domain.pay.application.service;

import blaybus.domain.pay.presentation.dto.kakao.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayReadyResponse;

public interface BlaybusPayService {

    KakaoPayReadyResponse payReady(String orderId, String userId, int amount);
    KakaoPayApproveResponse payApprove(String orderId, String userId,  String pgToken);
    KakaoPayOrderResponse getOrder(String tid);

    String randomOrderId();
    void save(KakaoPayReadyResponse kakaoPayReadyResponse, String orderId);

}
