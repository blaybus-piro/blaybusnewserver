package blaybus.domain.oauth2.infra.filter;

import blaybus.domain.oauth2.infra.exception.DuplicateLoginException;
import blaybus.domain.oauth2.infra.exception.InvalidAccessTokenException;
import blaybus.domain.user.domain.entity.Role;
import blaybus.global.jwt.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class BlaybusJWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final List<String> excludedPaths;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        if(requestURI.contains("/oauth2/authorization")) {
            String accessToken = jwtUtil.getAccessTokenFromHeaders(request);
            if(jwtUtil.jwtVerify(accessToken, "access")) {
                throw new DuplicateLoginException();
            }
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = jwtUtil.getAccessTokenFromHeaders(request);

        if(accessToken == null || accessToken.equals("undefined") || accessToken.equals("null")) {
            filterChain.doFilter(request, response);
            return;
        }

        if(!jwtUtil.jwtVerify(accessToken, "access")) {
            throw new InvalidAccessTokenException();
        }

        String userId = jwtUtil.getId(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getValue());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, Collections.singleton(authority));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern ->
                        new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
