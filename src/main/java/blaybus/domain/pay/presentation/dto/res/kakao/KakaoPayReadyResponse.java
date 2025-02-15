package blaybus.domain.pay.presentation.dto.res.kakao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public record KakaoPayReadyResponse(
        String tid,
        String next_redirect_pc_url,
        String next_redirect_mobile_url,
        Date created_at
) {
}
