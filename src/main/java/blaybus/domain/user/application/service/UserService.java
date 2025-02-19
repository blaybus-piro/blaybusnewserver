package blaybus.domain.user.application.service;

import blaybus.domain.user.domain.entity.User;

public interface UserService {
    User getUserById(String userId);
}