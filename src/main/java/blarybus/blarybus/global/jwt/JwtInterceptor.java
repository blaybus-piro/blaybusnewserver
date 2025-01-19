package blarybus.blarybus.global.jwt;

import blarybus.blarybus.global.exception.CustomException;
import blarybus.blarybus.global.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            jwtUtil.verify(jwtToken);

            String id = jwtUtil.getId(jwtToken);
            request.setAttribute("id", id);
            return true;
        }
        throw new CustomException(ErrorCode.UNAUTHORIZED);
    }
}
