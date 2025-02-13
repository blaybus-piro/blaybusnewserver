package blaybus.domain.oauth2.application.service;

import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2UserResponse;

import java.util.Map;

public interface GoogleUserCreateService {
    Map<String, String> createGoogleUser(OAuth2TokenResponse oAuth2TokenResponse, OAuth2UserResponse oAuth2UserResponse);
}
