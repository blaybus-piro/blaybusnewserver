package blaybus.domain.oauth2.infra.filter;

import blaybus.domain.oauth2.infra.exception.InvalidRefreshTokenException;
import blaybus.domain.oauth2.infra.exception.RefreshTokenNotExistException;
import blaybus.global.jwt.domain.repository.JsonWebTokenRepository;
import blaybus.global.jwt.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class BlaybusLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final JsonWebTokenRepository jsonWebTokenRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if(!(requestURI.equals("/api/oauth2/logout") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtUtil.getRefreshTokenFromCookies(request);

        if(!jwtUtil.jwtVerify(refreshToken, "refresh")) {
            throw new InvalidRefreshTokenException();
        }

        LogoutProcess(refreshToken, response);
    }

    private void LogoutProcess(String refreshToken, HttpServletResponse response) {
        if(!jsonWebTokenRepository.existsById(refreshToken)) {
            throw new RefreshTokenNotExistException();
        }
        jsonWebTokenRepository.deleteById(refreshToken);

        response.addHeader("Authorization", "Bearer ");

        ResponseCookie refreshTokeCookie = jwtUtil.invalidRefreshToken();
        response.addHeader(HttpHeaders.SET_COOKIE, refreshTokeCookie.toString());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
