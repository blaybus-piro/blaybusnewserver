package blaybus.domain.map.application.exception;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class MultiResponseException extends BlaybusException {
  public MultiResponseException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "여러개의 주소 결과가 도출되었습니다.");
  }
}
