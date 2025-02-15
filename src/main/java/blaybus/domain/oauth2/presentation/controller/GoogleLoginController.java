package blaybus.domain.oauth2.presentation.controller;

import blaybus.domain.oauth2.application.service.GoogleLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/oauth2")
@RequiredArgsConstructor
public class GoogleLoginController {

    private final GoogleLoginService googleLoginService;

    @PostMapping("/callback")
    public void login(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        googleLoginService.login(code, response);
    }
}
