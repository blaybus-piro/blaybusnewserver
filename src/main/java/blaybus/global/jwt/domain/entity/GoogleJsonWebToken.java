package blaybus.global.jwt.domain.entity;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "GoogleJsonWebToken")
@Builder
@Getter
public class GoogleJsonWebToken {
    @Id
    private String userId;

    private String accessToken;

    private String refreshToken;
}
