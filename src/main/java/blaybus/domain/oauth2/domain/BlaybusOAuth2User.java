package blaybus.domain.oauth2.domain;

import blaybus.domain.user.domain.entity.Role;
import blaybus.domain.user.presentation.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class BlaybusOAuth2User implements OAuth2User {

    private final UserDTO userDTO;
    private final Role role;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "id", userDTO.id(),
                "name", userDTO.name(),
                "email", userDTO.email(),
                "profile", userDTO.profile()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getValue()));
    }

    @Override
    public String getName() {
        return userDTO.name();
    }

    public String getId() {
        return userDTO.id();
    }

    public String getEmail() {
        return userDTO.email();
    }
}
