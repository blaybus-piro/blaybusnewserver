package blaybus.domain.pay.application.service.impl;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.domain.entity.BlaybusPayTid;
import blaybus.domain.pay.domain.repository.BlaybusPayRepository;
import blaybus.domain.pay.infra.exception.BlaybusPayException;
import blaybus.domain.pay.infra.exception.BlaybusPayTidException;
import blaybus.domain.pay.infra.feignclient.KakaoPayClient;
import blaybus.domain.pay.presentation.dto.req.ReadyRequest.ReadyRequestDTO;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.res.kakao.KakaoPayReadyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlaybusPayServiceImpl implements BlaybusPayService {

    private final BlaybusPayRepository blaybusPayRepository;
    private final KakaoPayClient kakaoPayClient;

    @Value("${kakao.pay.admin-key}")
    private String adminKey;  // 카카오 관리자 키

    @Value("${kakao.pay.cid}")
    private String cid;       // 가맹점 CID 테스트

    @Value("${server.url}")
    private String serverUrl;


    /**
     * 0) 결제 준비 전
     * -
     */

    public KakaoPayReadyResponse payLogic(String userId, ReadyRequestDTO reqDto) {
        String orderId = randomOrderId();
        KakaoPayReadyResponse response = payReady(orderId, userId, reqDto.amount());
        // tid 값 임시 저장
        BlaybusPayTid blaybusPayTid = BlaybusPayTid.builder()
                .id(orderId)
                .tid(response.tid())
                .build();
        blaybusPayRepository.save(blaybusPayTid);

        return response;
    }

    /**
     * 1) 결제 준비
     * - 결제 요청에 필요한 파라미터를 세팅하고, KakaoPayClient.ready() 호출
     */
    @Override
    public KakaoPayReadyResponse payReady(String orderId, String userId, int amount) {

        String authorization = "KakaoAK " + adminKey;
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        // 파라미터
        String itemName = "디자이너와의 컨설팅";
        int quantity = 1;
        int vatAmount = 0;
        int taxFreeAmount = 0;

        // env에서 serverUrl 불러옴!
        String approvalUrl = serverUrl + "/api/pay/approve?orderId=" + orderId;
        String cancelUrl = serverUrl + "/api/pay/approve?orderId=" + orderId;
        String failUrl = serverUrl + "/api/pay/approve?orderId=" + orderId;

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
     * - 사용자 결제 완료 후 pg_token을 받아 최종 승인
     */
    @Override
    public KakaoPayApproveResponse payApprove(String orderId, String userId, String pgToken) {
        String authorization = "KakaoAK " + adminKey;
        String contentType = "application/x-www-form-urlencoded;charset=utf-8";

        Optional<BlaybusPayTid> findTid = blaybusPayRepository.findById(orderId);

        BlaybusPayTid blaybusPayTid = findTid.get();

        String tid = blaybusPayTid.getTid();
        if (tid == null) {
            throw new BlaybusPayTidException();
        }

        KakaoPayApproveResponse approve;
        try {
            // 결제 승인 요청
            approve = kakaoPayClient.approve(
                    authorization,
                    contentType,
                    cid,
                    tid,
                    orderId,
                    userId,
                    pgToken
            );
            approve.setStatus("SUCCESS");  // 결제 성공 시 status 설정
        } catch (BlaybusPayException e) {
            approve = new KakaoPayApproveResponse();
            approve.setStatus("FAIL");
        }

        // tid 삭제
        blaybusPayRepository.delete(blaybusPayTid);
        return approve;

    }

    /**
     * 3) 단건 조회
     * - tid로 결제 정보를 조회
     */
    @Override
    public KakaoPayOrderResponse getOrder(String tid) {
        String authorization = "KakaoAK " + adminKey;
        return kakaoPayClient.getOrder(authorization, cid, tid);
    }


    /**
     * 4) 주문 번호 생성
     * - 주문 번호 생성 payReady에서 해도 되지만 값을 저장할때 orderId를 몰라서 따로 메서드 만들어줘야됨
     */
    @Override
    public String randomOrderId() {
        return UUID.randomUUID().toString();
    }



}