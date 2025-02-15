package blaybus.domain.meeting.infra.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMeetFeignConfig {
    @Bean
    public RequestInterceptor authorizationInterceptor() {
        return requestTemplate -> {
            if (!requestTemplate.url().contains("/v1/payment/")) {
                requestTemplate.header("Authorization", "Bearer " + "your-oauth-token");
            }
        };
    }
}
