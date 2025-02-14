package blaybus.domain.oauth2.application.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface GoogleLoginService {
    void login(String code, HttpServletResponse response) throws IOException;
}
