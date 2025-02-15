package blaybus.domain.pay.presentation.dto.res.kakao;

import lombok.Getter;
import lombok.Setter;


// 카카오페이에서 내려주는 필드들
public record KakaoPayOrderResponse(
        String tid,
        String cid,
        String status
) {

}
