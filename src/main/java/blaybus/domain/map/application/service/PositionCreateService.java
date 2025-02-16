package blaybus.domain.map.application.service;

import java.io.UnsupportedEncodingException;

public interface PositionCreateService {
    void createPosition(String name) throws UnsupportedEncodingException;
}
