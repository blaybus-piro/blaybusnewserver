package blaybus.domain.oauth2.presentation.dto.response.impl;

import blaybus.domain.oauth2.presentation.dto.response.OAuth2Response;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class NaverOAuth2Response implements OAuth2Response {
    private final Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("nickname").toString();
    }

    @Override
    public String getProfile() {
        return attributes.get("profile_image").toString();
    }
}
