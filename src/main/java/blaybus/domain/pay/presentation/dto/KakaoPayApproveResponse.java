package blaybus.domain.pay.presentation.dto;


import blaybus.domain.pay.presentation.dto.amount.Amount;
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
    private String item_name;
    private int quantity;
    private Date created_at;
    private Date approved_at;
    // 필요 시 필드 추가
}
