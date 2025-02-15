package blaybus.domain.pay.infra.feignclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
public class FeignConfig {

    @Value("${kakao.pay.admin-key}")
    private String adminKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 🔥 KakaoPay API 요청이면 Authorization 헤더 덮어쓰기
                if (template.url().contains("/v1/payment/")) {
                    template.headers().remove("Authorization");  // 🔥 기존 Authorization 제거
                    template.header("Authorization", "KakaoAK " + adminKey);  // 🔥 KakaoPay 방식으로 설정

                    log.info("🔥 KakaoPay API 요청 → Authorization 강제 덮어쓰기: KakaoAK {}", adminKey);
                }

                log.info("🔥 Feign 최종 요청: {} {}", template.method(), template.url());
                log.info("🔥 Feign Headers: {}", template.headers());
            }
        };
    }
}

