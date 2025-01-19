package blarybus.blarybus.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ConfigController {

    @GetMapping("/")
    public String home(Model model){
        return "home/index";
    }
}
