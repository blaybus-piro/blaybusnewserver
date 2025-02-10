package blaybus.domain.oauth2.presentation.dto.response.impl;

import blaybus.domain.oauth2.presentation.dto.response.OAuth2Response;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class GoogleOAuth2Response implements OAuth2Response {

    private final Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getProfile() {
        return attributes.get("picture").toString();
    }
}