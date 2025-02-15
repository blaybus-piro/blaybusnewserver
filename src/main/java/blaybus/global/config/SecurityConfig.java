package blaybus.global.config;

import blaybus.domain.oauth2.infra.filter.BlaybusJWTFilter;
import blaybus.domain.oauth2.infra.filter.BlaybusLogoutFilter;
import blaybus.global.infra.exception.auth.BlaybusAuthExceptionFilter;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import blaybus.global.jwt.domain.repository.JsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JsonWebTokenRepository jsonWebTokenRepository;
    private final GoogleJsonWebTokenRepository googleJsonWebTokenRepository;
    private final List<String> excludedUrls = Arrays.asList("/api/reissue", "/api/oauth2/login", "/api/healthcheck", "/api/oauth2/callback");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((url) -> url
                        .requestMatchers("/api/healthcheck").permitAll()
                        .requestMatchers("/api/oauth2/login").permitAll()
                        .requestMatchers("/api/oauth2/callback").permitAll()
                        .requestMatchers("/api/reissue").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement((session) ->  session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(except -> except
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                        ))
                .addFilterAfter(new BlaybusAuthExceptionFilter(objectMapper), CorsFilter.class)
                .addFilterAfter(new BlaybusJWTFilter(jwtUtil, excludedUrls), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new BlaybusLogoutFilter(jwtUtil, jsonWebTokenRepository, googleJsonWebTokenRepository), LogoutFilter.class);

        return http.build();
    }
}
