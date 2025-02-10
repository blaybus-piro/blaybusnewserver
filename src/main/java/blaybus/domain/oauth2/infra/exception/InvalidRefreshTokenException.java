package blaybus.domain.oauth2.infra.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends BlaybusAuthException {
    public InvalidRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, "Refresh Token이 유효하지 않습니다.");
    }
}
