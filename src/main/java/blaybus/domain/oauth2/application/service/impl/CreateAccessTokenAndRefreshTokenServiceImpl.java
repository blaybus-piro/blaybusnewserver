package blaybus.domain.oauth2.application.service.impl;

import blaybus.domain.oauth2.application.service.CreateAccessTokenAndRefreshTokenService;
import blaybus.domain.user.domain.entity.Role;
import blaybus.global.jwt.domain.entity.JsonWebToken;
import blaybus.global.jwt.domain.repository.JsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateAccessTokenAndRefreshTokenServiceImpl implements CreateAccessTokenAndRefreshTokenService {

    private final JWTUtil jwtUtil;
    private final JsonWebTokenRepository jsonWebTokenRepository;

    @Override
    public Map<String, String> createAccessTokenAndRefreshToken(String userId, Role role, String email) {
        String accessToken = jwtUtil.createAccessToken(userId, role, email);
        String refreshToken = jwtUtil.createRefreshToken(userId, role, email);

        JsonWebToken jsonWebToken = JsonWebToken.builder()
                .refreshToken(refreshToken)
                .providerId(userId)
                .role(role)
                .email(email)
                .build();

        jsonWebTokenRepository.save(jsonWebToken);

        String refreshTokenCookie = jwtUtil.createRefreshTokenCookie(refreshToken).toString();

        return Map.of("access_token", accessToken, "refresh_token_cookie", refreshTokenCookie);
    }
}
