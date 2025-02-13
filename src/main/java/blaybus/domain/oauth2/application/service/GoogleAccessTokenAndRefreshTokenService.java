package blaybus.domain.oauth2.application.service;

import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;

public interface GoogleAccessTokenAndRefreshTokenService {
    OAuth2TokenResponse getAccessTokenAndRefreshToken(String code);
}
