package blaybus.domain.pay.infra.exception;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class BlaybusPayTidException extends BlaybusException {
    public BlaybusPayTidException() {
        super(HttpStatus.PAYMENT_REQUIRED, "TID 값이 존재하지 않습니다.");
    }
}
