package personal.practice.skill_tech.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String goHome() {

        return "/views/index";
    }

    @GetMapping("/login")
    public String login() {

        return "/views/login";
    }
}
