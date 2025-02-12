package blaybus.pay;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.presentation.controller.BlaybusPayController;
import blaybus.domain.pay.presentation.dto.KakaoPayApproveResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayOrderResponse;
import blaybus.domain.pay.presentation.dto.KakaoPayReadyResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BlaybusPayController.class)
public class PayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // BlaybusPayService를 목으로 등록하여 실제 호출 없이 원하는 결과 리턴
    @MockBean
    private BlaybusPayService blaybusPayService;

    @Test
    public void testPayReady() throws Exception {
        // 1) 결제 준비에 대한 가짜 응답 생성
        KakaoPayReadyResponse readyResponse = new KakaoPayReadyResponse();
        readyResponse.setTid("tid123");
        readyResponse.setNext_redirect_pc_url("http://example.com/redirect");

        // 서비스 메서드 호출시 readyResponse를 반환하도록 설정
        when(blaybusPayService.payReady(eq("123"), eq("U001"), eq(1000))).thenReturn(readyResponse);

        // GET /api/pay/ready 호출 및 응답 검증
        mockMvc.perform(get("/api/pay/ready")
                        .param("orderId", "123")
                        .param("userId", "U001")
                        .param("amount", "1000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tid", is("tid123")))
                .andExpect(jsonPath("$.next_redirect_pc_url", is("http://example.com/redirect")));
    }

    @Test
    public void testPayApprove() throws Exception {
        // 2) 결제 승인에 대한 가짜 응답 생성
        KakaoPayApproveResponse approveResponse = new KakaoPayApproveResponse();
        approveResponse.setAid("aid123");
        approveResponse.setTid("tid123");

        // payApprove 메서드 호출시 approveResponse를 반환하도록 설정
        // @AuthenticationPrincipal으로 주입되는 userId는 OAuth2 로그인 시 설정된 principal의 id가 사용됨.
        when(blaybusPayService.payApprove(eq("123"), anyString(), eq("tid123"), eq("pgToken123")))
                .thenReturn(approveResponse);

        // GET /api/pay/approve 호출 시 oauth2Login()을 사용하여 사용자 id("U001")를 설정
        mockMvc.perform(get("/api/pay/approve")
                        .param("orderId", "123")
                        .param("tid", "tid123")
                        .param("pgToken", "pgToken123")
                        .with(oauth2Login().id("U001")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aid", is("aid123")))
                .andExpect(jsonPath("$.tid", is("tid123")));
    }

    @Test
    public void testGetOrder() throws Exception {
        // 3) 결제 단건 조회에 대한 가짜 응답 생성
        KakaoPayOrderResponse orderResponse = new KakaoPayOrderResponse();
        orderResponse.setTid("tid123");
        orderResponse.setStatus("approved");

        when(blaybusPayService.getOrder(eq("tid123"))).thenReturn(orderResponse);

        // GET /api/pay/order 호출 및 응답 검증
        mockMvc.perform(get("/api/pay/order")
                        .param("tid", "tid123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tid", is("tid123")))
                .andExpect(jsonPath("$.status", is("approved")));
    }
}
