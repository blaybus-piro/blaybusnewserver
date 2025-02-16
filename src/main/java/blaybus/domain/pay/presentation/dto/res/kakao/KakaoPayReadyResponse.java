package blaybus.domain.pay.presentation.dto.res.kakao;


import java.util.Date;

public record KakaoPayReadyResponse(
        String tid,
        String next_redirect_pc_url,
        String next_redirect_mobile_url,
        Date created_at
) {
}
