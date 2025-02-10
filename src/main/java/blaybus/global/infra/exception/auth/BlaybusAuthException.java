package blaybus.global.infra.exception.auth;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class BlaybusAuthException extends BlaybusException {
    public BlaybusAuthException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}