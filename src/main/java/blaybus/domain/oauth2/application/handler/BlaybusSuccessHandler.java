package blaybus.domain.oauth2.application.handler;

import blaybus.domain.oauth2.domain.BlaybusOAuth2User;
import blaybus.domain.user.domain.entity.Role;
import blaybus.global.jwt.domain.entity.JsonWebToken;
import blaybus.global.jwt.domain.repository.JsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class BlaybusSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final JsonWebTokenRepository jsonWebTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        BlaybusOAuth2User user = (BlaybusOAuth2User) authentication.getPrincipal();

        String userId = user.getId();

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority authority = iterator.next();
        Role role = Role.getByValue(authority.getAuthority());

        String mail = user.getEmail();

        String accessToken = jwtUtil.createAccessToken(userId, role, mail);
        String refreshToken = jwtUtil.createRefreshToken(userId, role, mail);

        JsonWebToken jsonWebToken = JsonWebToken.builder()
                .refreshToken(refreshToken)
                .providerId(user.getId())
                .role(role)
                .email(mail)
                .build();

        jsonWebTokenRepository.save(jsonWebToken);

        ResponseCookie accessTokenCookie = jwtUtil.createAccessCookie(accessToken);
        ResponseCookie refreshTokenCookie = jwtUtil.createRefreshTokenCookie(refreshToken);

        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
    }
}
