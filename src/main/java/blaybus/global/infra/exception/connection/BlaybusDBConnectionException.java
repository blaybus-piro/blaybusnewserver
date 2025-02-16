package blaybus.global.infra.exception.connection;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class BlaybusDBConnectionException extends BlaybusException {
    public BlaybusDBConnectionException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "DB Connection 과정 중 문제가 발생했습니다.");
    }
}
