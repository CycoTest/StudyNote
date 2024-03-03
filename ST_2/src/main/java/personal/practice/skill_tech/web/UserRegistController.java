package personal.practice.skill_tech.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.practice.skill_tech.service.UserService;
import personal.practice.skill_tech.web.dto.UserRegistrDto;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrDto userRegirstDto() {

        return new UserRegistrDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrDto());

        return "/views/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrDto regirstDto) {
        userService.save(regirstDto);

        return "redirect:/registration?success";
    }
}
