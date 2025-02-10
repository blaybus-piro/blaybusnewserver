package blaybus.global.infra.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationCheckController {
    @GetMapping("/authcheck")
    @ResponseStatus(HttpStatus.OK)
    public void authcheck() {}
}
