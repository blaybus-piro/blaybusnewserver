package blaybus.domain.pay.presentation.dto.res.kakao.amount;

import lombok.Getter;
import lombok.Setter;

public record Amount(
        int total,
        int taxFree,
        int vat,
        int point,
        int discount
) {}