package blaybus.domain.pay.application.service;

import blaybus.domain.pay.domain.entity.BlaybusPayTid;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayReadyResponse;

import java.util.Optional;

public interface BlaybusPayService {

    KakaoPayReadyResponse payReady(String orderId, String userId, int amount);
    KakaoPayApproveResponse payApprove(String orderId, String userId, String tid, String pgToken);
    KakaoPayOrderResponse getOrder(String tid);

    void save(BlaybusPayTid blaybusPayTid);

    BlaybusPayTid findById(String orderId);

    void delete(BlaybusPayTid blaybusPayTid);
}
