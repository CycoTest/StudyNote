package personal.practice.skill_tech.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import personal.practice.skill_tech.service.UserService;
import personal.practice.skill_tech.web.dto.UserRegirstDto;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class UserRegistController {

    private final UserService userService;

    @ModelAttribute("user")
    public UserRegirstDto userRegirstDto() {

        return new UserRegirstDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegirstDto());

        return "/views/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegirstDto regirstDto) {
        userService.save(regirstDto);

        return "redirect:/registration?success";
    }
}
