package blaybus.domain.user.application.service.impl;

import blaybus.domain.user.domain.entity.User;
import blaybus.domain.user.domain.repository.UserRepository;
import blaybus.domain.user.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import blaybus.domain.user.domain.exception.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
