package blaybus.domain.pay.infra.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "kakao-pay-service", url = "https://kakaopayurl")
public interface KakaoPayClient {

}
