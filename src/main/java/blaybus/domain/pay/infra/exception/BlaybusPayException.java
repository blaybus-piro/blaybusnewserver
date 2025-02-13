package blaybus.domain.pay.infra.exception;

import org.springframework.http.HttpStatus;

public class BlaybusPayException extends RuntimeException {
    private final HttpStatus status;

    public BlaybusPayException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}