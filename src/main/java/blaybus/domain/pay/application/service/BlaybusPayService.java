package blaybus.domain.pay.application.service;

import blaybus.domain.pay.presentation.dto.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayReadyResponse;

public interface BlaybusPayService {

    KakaoPayReadyResponse payReady(String orderId, String userId, int amount);
    KakaoPayApproveResponse payApprove(String orderId, String userId, String tid, String pgToken);
    KakaoPayOrderResponse getOrder(String tid);
}
