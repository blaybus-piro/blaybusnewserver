package blaybus.domain.pay.infra.exception;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class BlaybusPayException extends BlaybusException {


    public BlaybusPayException() {
        super(HttpStatus.PAYMENT_REQUIRED, "결제가 실패됐습니다.");
    }


}