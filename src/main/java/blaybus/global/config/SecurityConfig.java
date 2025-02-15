package blaybus.global.config;

import blaybus.domain.oauth2.application.handler.BlaybusFailureHandler;
import blaybus.domain.oauth2.application.handler.BlaybusSuccessHandler;
import blaybus.domain.oauth2.application.service.BlaybusOAuth2UserService;
import blaybus.domain.oauth2.infra.filter.BlaybusJWTFilter;
import blaybus.domain.oauth2.infra.filter.BlaybusLogoutFilter;
import blaybus.global.infra.exception.auth.BlaybusAuthExceptionFilter;
import blaybus.global.jwt.domain.repository.JsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final BlaybusOAuth2UserService blaybusOAuth2UserService;
    private final BlaybusSuccessHandler blaybusSuccessHandler;
    private final BlaybusFailureHandler blaybusFailureHandler;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JsonWebTokenRepository jsonWebTokenRepository;
    private final List<String> excludedUrls = Arrays.asList("" +
            "/api/reissue", "favicon.ico", "/api/healthcheck","/**");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(blaybusOAuth2UserService))
                        .successHandler(blaybusSuccessHandler)
                        .failureHandler(blaybusFailureHandler)
                )
                .authorizeHttpRequests((url) -> url
                        .requestMatchers("/api/healthcheck").permitAll()
                        .requestMatchers("/api/reissue").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement((session) ->  session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(new BlaybusAuthExceptionFilter(objectMapper), CorsFilter.class)
                .addFilterAfter(new BlaybusJWTFilter(jwtUtil, excludedUrls), OAuth2LoginAuthenticationFilter.class)
                .addFilterAt(new BlaybusLogoutFilter(jwtUtil, jsonWebTokenRepository), LogoutFilter.class);

        return http.build();
    }
}
