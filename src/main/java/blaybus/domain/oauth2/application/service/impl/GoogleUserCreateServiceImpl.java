package blaybus.domain.oauth2.application.service.impl;

import blaybus.domain.oauth2.application.service.GoogleUserCreateService;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2TokenResponse;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2UserResponse;
import blaybus.domain.user.domain.entity.Role;
import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.global.jwt.domain.entity.GoogleJsonWebToken;
import blaybus.global.jwt.domain.repository.GoogleJsonWebTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GoogleUserCreateServiceImpl implements GoogleUserCreateService {

    private final UserRepository userRepository;
    private final GoogleJsonWebTokenRepository googleJsonWebTokenRepository;

    @Override
    public Map<String, String> createGoogleUser(OAuth2TokenResponse oAuth2TokenResponse, OAuth2UserResponse oAuth2UserResponse) {
        User user = userRepository.findById(oAuth2UserResponse.id()).orElse(null);

        if(user == null) {
            log.info("신규 고객님, {} 님이 입장하셨습니다.", oAuth2UserResponse.name());
            user = User.builder()
                    .id(oAuth2UserResponse.id())
                    .mail(oAuth2UserResponse.email())
                    .name(oAuth2UserResponse.name())
                    .profile(oAuth2UserResponse.profile())
                    .role(Role.USER)
                    .build();
        }
        else {
            user.updateNameAndEmailAndProfile(oAuth2UserResponse.name(), oAuth2UserResponse.email(), oAuth2UserResponse.profile());
        }

        userRepository.save(user);

        LocalDateTime now = LocalDateTime.now().plusSeconds(oAuth2TokenResponse.expiresIn());

        GoogleJsonWebToken googleJsonWebToken = GoogleJsonWebToken.builder()
                .userId(user.getId())
                .accessToken(oAuth2TokenResponse.accessToken())
                .refreshToken(oAuth2TokenResponse.refreshToken())
                .expiresIn(now)
                .build();

        googleJsonWebTokenRepository.deleteById(user.getId());
        googleJsonWebTokenRepository.save(googleJsonWebToken);

        return Map.of(
                "id", user.getId(),
                "role", user.getRole().toString(),
                "email", user.getMail()
                );
    }
}
