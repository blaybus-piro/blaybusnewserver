package blaybus.global.infra.feignclient;

import blaybus.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "GoogleOAuth2UserInfo",
        url = "https://www.googleapis.com"
)
public interface GoogleOAuth2UserFeignClient {

    @GetMapping(value = "/oauth2/v3/userinfo")
    OAuth2UserResponse getUserInfo(@RequestHeader("Authorization") String accessToken);

}