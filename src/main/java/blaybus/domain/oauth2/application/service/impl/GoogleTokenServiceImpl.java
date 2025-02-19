package blaybus.domain.oauth2.application.service.impl;

import blaybus.domain.oauth2.application.service.GoogleAccessTokenAndRefreshTokenService;
import blaybus.domain.oauth2.application.service.GoogleTokenService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class GoogleTokenServiceImpl implements GoogleTokenService {
    private final GoogleJsonWebTokenRepository googleTokenRepository;
    private final GoogleAccessTokenAndRefreshTokenService tokenService;

    @Override
    public String getValidAccessToken(String userId) {
        GoogleJsonWebToken token = googleTokenRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Google 토큰을 찾을 수 없습니다."));

        if (token.getExpiresIn() == null || token.getExpiresIn().minusMinutes(10).isBefore(LocalDateTime.now())) {
            OAuth2TokenResponse newToken = tokenService.refreshAccessToken(token.getRefreshToken());

            // 새 토큰 저장
            GoogleJsonWebToken updatedToken = GoogleJsonWebToken.builder()
                    .userId(userId)
                    .accessToken(newToken.accessToken())
                    .refreshToken(token.getRefreshToken()) // refresh token은 유지
                    .expiresIn(LocalDateTime.now().plusHours(1))
                    .build();
            googleTokenRepository.save(updatedToken);

            return "Bearer " + newToken.accessToken();
        }
        return "Bearer " + token.getAccessToken();
    }
}
