package blaybus.domain.oauth2.infra.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class InvalidAccessTokenException extends BlaybusAuthException {
    public InvalidAccessTokenException() {
        super(HttpStatus.UNAUTHORIZED, "Access Token이 유효하지 않습니다.");
    }
}
