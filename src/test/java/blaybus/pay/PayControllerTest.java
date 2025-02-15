package blaybus.pay;

import blaybus.domain.pay.application.service.BlaybusPayService;
import blaybus.domain.pay.presentation.controller.BlaybusPayController;
import blaybus.domain.pay.presentation.dto.kakao.KakaoPayReadyResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BlaybusPayController.class)
public class PayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlaybusPayService blaybusPayService;

    @Test
    @DisplayName("결제 준비 API 테스트")
    void testPayReady() throws Exception {
        // Given
        String orderId = "order1234";
        String userId = "user5678";
        int amount = 1000;

        KakaoPayReadyResponse mockResponse = new KakaoPayReadyResponse(
                "tid12345",
                "https://mock.kakao.com/payment/redirect",
                "https://mock.kakao.com/payment/next_redirect_pc_url",
                "https://mock.kakao.com/payment/next_redirect_mobile_url"
        );

        Mockito.when(blaybusPayService.payReady(any(), any(), any()))
                .thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(get("/api/pay/ready")
                        .param("orderId", orderId)
                        .param("userId", userId)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tid").value("tid12345"))
                .andExpect(jsonPath("$.nextRedirectUrl").value("https://mock.kakao.com/payment/redirect"));
    }
}
