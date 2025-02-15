package blaybus.domain.oauth2.application.service;

import blaybus.domain.user.domain.entity.Role;

import java.util.Map;

public interface CreateAccessTokenAndRefreshTokenService {
    Map<String, String> createAccessTokenAndRefreshToken(String userId, Role role, String email);
}
