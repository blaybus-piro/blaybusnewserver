package blaybus.global.jwt.util.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class BlaybusJWTExpiredException extends BlaybusAuthException {
    public BlaybusJWTExpiredException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
