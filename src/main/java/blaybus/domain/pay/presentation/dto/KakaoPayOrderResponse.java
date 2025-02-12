package blaybus.domain.pay.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 카카오페이에서 내려주는 필드들
public class KakaoPayOrderResponse {
    private String tid;
    private String cid;
    private String status;

}
