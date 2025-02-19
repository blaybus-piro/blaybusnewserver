package blaybus.domain.user.presentation.controller;

import blaybus.domain.user.application.service.UserService;
import blaybus.domain.user.domain.entity.User;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public Map<String, String> getUserInfo(@PathVariable String userId) {
        User user = userService.getUserById(userId);

        Map<String, String> response = new HashMap<>();
        response.put("name", user.getName());
        response.put("mail", user.getMail());

        return response;
    }
}