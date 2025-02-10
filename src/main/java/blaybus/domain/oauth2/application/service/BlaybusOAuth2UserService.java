package blaybus.domain.oauth2.application.service;

import blaybus.domain.oauth2.domain.BlaybusOAuth2User;
import blaybus.domain.oauth2.presentation.dto.response.OAuth2Response;
import blaybus.domain.oauth2.presentation.dto.response.impl.GoogleOAuth2Response;
import blaybus.domain.user.domain.entity.Role;
import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.domain.user.presentation.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@Component
public class BlaybusOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oauth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(oauth2UserRequest);

        OAuth2Response response = getOAuth2Response(oauth2UserRequest, oauth2User);

        User user = userRepository.findById(response.getProviderId()).orElse(null);

        if(user == null) {
            log.info("신규 고객님, {} 님이 입장하셨습니다.", response.getName());
            user = User.builder()
                    .id(response.getProvider() + " " + response.getProviderId())
                    .mail(response.getEmail())
                    .name(response.getName())
                    .profile(response.getProfile())
                    .role(Role.USER)
                    .build();
        }
        else {
            user.updateNameAndEmailAndProfile(response.getName(), response.getEmail(), response.getProfile());
        }

        userRepository.save(user);

        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getMail(), user.getProfile());

        return new BlaybusOAuth2User(userDTO, user.getRole());
    }

    private OAuth2Response getOAuth2Response(OAuth2UserRequest oauth2UserRequest, OAuth2User oauth2User) {
        String registrationId = oauth2UserRequest.getClientRegistration().getRegistrationId();

        return switch (registrationId) {
            case "google" -> new GoogleOAuth2Response(oauth2User.getAttributes());
            default -> throw new OAuth2AuthenticationException("현재 고객님의 OAuth2 인증 서비스는 지원되지 않습니다.");
        };
    }
}
