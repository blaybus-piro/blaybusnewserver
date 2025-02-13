package blaybus.domain.oauth2.application.service.impl;

import blaybus.domain.oauth2.application.service.GoogleAccessTokenAndRefreshTokenService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.global.infra.feignclient.GoogleOAuth2URLFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleAccessTokenAndRefreshTokenServiceImpl implements GoogleAccessTokenAndRefreshTokenService {

    private final GoogleOAuth2URLFeignClient googleOAuth2URLFeignClient;

    @Value("${oauth2.client-id}")
    private String clientId;

    @Value("${oauth2.client-secret}")
    private String clientSecret;

    @Value("${oauth2.redirect-uri}")
    private String redirectUri;

    @Override
    public OAuth2TokenResponse getAccessTokenAndRefreshToken(String code) {
        return googleOAuth2URLFeignClient.getAccessToken(
                code,
                clientId,
                clientSecret,
                redirectUri,
                "authorization_code"
        );
    }
}
