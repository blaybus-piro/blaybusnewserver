package blaybus.global.infra.exception;

import java.time.LocalDateTime;

public record ErrorResponse (
        int status,
        String message,
        String cause,
        LocalDateTime time
) {
    public static ErrorResponse of(BlaybusException exception) {
        return new ErrorResponse(
                exception.getStatus().value(),
                exception.getMessage(),
                exception.getCause().getMessage(),
                LocalDateTime.now()
        );
    }

    public static ErrorResponse of(int status, String message, String cause) {
        return new ErrorResponse(status, message, cause, LocalDateTime.now());
    }
}