package blaybus.domain.user.domain.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class InvalidRoleException extends BlaybusAuthException {
    public InvalidRoleException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
