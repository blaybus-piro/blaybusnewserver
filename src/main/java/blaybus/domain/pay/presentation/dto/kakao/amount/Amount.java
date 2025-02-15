package blaybus.domain.pay.presentation.dto.kakao.amount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {
    private int total;
    private int tax_free;
    private int vat;
    private int point;
    private int discount;
}