package blaybus.domain.oauth2.infra.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends BlaybusAuthException {
    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "중복 로그인은 허용되지 않습니다.");
    }
}
