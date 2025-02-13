package blaybus.domain.oauth2.application.service.impl;

import blaybus.domain.oauth2.application.service.GoogleUserService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import blaybus.global.infra.feignclient.GoogleOAuth2UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleUserServiceImpl implements GoogleUserService {

    private final GoogleOAuth2UserFeignClient googleOAuth2UserFeignClient;

    @Override
    public OAuth2UserResponse getUser(String accessToken) {
        return googleOAuth2UserFeignClient.getUserInfo("Bearer " + accessToken);
    }
}
