package blaybus.domain.oauth2.application.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import org.springframework.http.HttpStatus;

public class BlaybusAccessDeniedException extends BlaybusAuthException {
    public BlaybusAccessDeniedException() {
        super(HttpStatus.UNAUTHORIZED, "OAuth2 로그인 과정 중 에러가 발생했습니다.");
    }
}
