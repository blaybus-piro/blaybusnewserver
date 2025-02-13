package blaybus.domain.meeting.infra.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleMeetFeignConfig {
    @Bean
    public RequestInterceptor authorizationInterceptor() {
        return requestTemplate -> {
            // OAuth2 토큰이 여기서 자동으로 추가됨
            requestTemplate.header("Authorization", "Bearer" + "${oauth2.token}");
        };
    }
}
