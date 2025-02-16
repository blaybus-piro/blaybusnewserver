package blaybus.domain.oauth2.application.service;

public interface GoogleTokenService {
    String getValidAccessToken(String userId);
}
