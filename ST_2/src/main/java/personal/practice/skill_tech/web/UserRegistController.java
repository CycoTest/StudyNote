package personal.practice.skill_tech.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.practice.skill_tech.service.UserService;
import personal.practice.skill_tech.web.dto.UserRegirstDto;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistController {

    private final UserService userService;

    public String registerUserAccount(@ModelAttribute("user")UserRegirstDto regirstDto) {

        userService.save(regirstDto);

        return "redirect:/registration?success";
    }
}
