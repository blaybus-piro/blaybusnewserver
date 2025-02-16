package blaybus.domain.map.application.exception;

import blaybus.global.infra.exception.BlaybusException;
import org.springframework.http.HttpStatus;

public class GeocodeAPIConnectException extends BlaybusException {
    public GeocodeAPIConnectException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "GeoCode API Connection 에서 에러가 발생했습니다.");
    }
}
