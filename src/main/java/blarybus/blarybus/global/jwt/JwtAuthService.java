package blarybus.blarybus.global.jwt;

import blarybus.blarybus.global.jwt.dto.JwtTokenSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthService {
    private final JwtUtil jwtUtil;

    public JwtTokenSet generateToken(String userIdx) {
        String token = jwtUtil.createToken(userIdx);

        JwtTokenSet jwtTokenSet = JwtTokenSet.builder()
                .token(token)
                .build();

        return jwtTokenSet;
    }
}
