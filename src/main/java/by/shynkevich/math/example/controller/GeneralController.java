package by.shynkevich.math.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    private static final String WELCOME_VIEW = "welcome";

    @GetMapping("/")
    public String main() {
        return WELCOME_VIEW;
    }
}
