package blaybus.domain.pay.presentation.dto.res.kakao;


import blaybus.domain.pay.presentation.dto.res.kakao.amount.Amount;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class KakaoPayApproveResponse {
    private String aid;
    private String tid;
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String payment_method_type;
    private Amount amount;  // 결제 금액 정보
    private String status; // 결제 완료 상태
    private String item_name;
    private int quantity;
    private Date created_at;
    private Date approved_at;

}
