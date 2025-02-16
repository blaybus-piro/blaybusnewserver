package blaybus.domain.pay.application.service;

import blaybus.domain.pay.presentation.dto.req.ReadyRequest.ReadyRequestDTO;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayReadyResponse;

public interface BlaybusPayService {

    KakaoPayReadyResponse payLogic(String userId, ReadyRequestDTO dto);

    KakaoPayReadyResponse payReady(String orderId, String userId, int amount);

    KakaoPayApproveResponse payApprove(String orderId, String userId, String pgToken);

    KakaoPayOrderResponse getOrder(String tid);


}
