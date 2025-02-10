package blaybus.global.infra.exception;

import blaybus.global.infra.exception.auth.BlaybusAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BlaybusException.class)
    public ResponseEntity<ErrorResponse> handleBlaybusException(BlaybusException e) {
        return ResponseEntity.status(e.getStatus()).body(ErrorResponse.of(e));
    }

    @ExceptionHandler(BlaybusAuthException.class)
    public ResponseEntity<ErrorResponse> handleBlaybusAuthException(BlaybusAuthException e) {
        ErrorResponse errorResponse = ErrorResponse.of(e.getStatus().value(), "인증관련 에러가 발생했습니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: ", e);
        ErrorResponse errorResponse = ErrorResponse.of(400, "요청 인자값이 올바르지 않습니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        ErrorResponse errorResponse = ErrorResponse.of(400, "요청 인자값이 유효하지 않습니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("AccessDeniedException: ", e);
        ErrorResponse errorResponse = ErrorResponse.of(403, "권한이 올바르지 않습니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException: ", e);
        ErrorResponse errorResponse = ErrorResponse.of(500, "서버에서 예상치 못한 오류가 발생했습니다.", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
