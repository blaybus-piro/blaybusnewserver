package blaybus.domain.oauth2.infra.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotExistException extends BlaybusAuthException {
    public RefreshTokenNotExistException() {
        super(HttpStatus.UNAUTHORIZED, "Refresh Token이 DB에 존재하지 않습니다.");
    }
}
