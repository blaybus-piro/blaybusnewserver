package blaybus.global.infra.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlaybusException extends RuntimeException {

    private final HttpStatus status;

    public BlaybusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
