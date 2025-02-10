package blaybus.global.jwt.util.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class BlaybusJWTException extends BlaybusAuthException {
    public BlaybusJWTException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
