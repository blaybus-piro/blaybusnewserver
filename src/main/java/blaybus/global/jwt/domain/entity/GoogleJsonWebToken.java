package blaybus.global.jwt.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@RedisHash(value = "GoogleJsonWebToken")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleJsonWebToken {
    @Id
    private String userId;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime expiresIn;
}
