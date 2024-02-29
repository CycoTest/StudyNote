package personal.practice.skill_tech.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import personal.practice.skill_tech.service.EmployeeService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHome(Model model) {
        log.info("employeeService = {}", employeeService.getAllEmployees());
        model.addAttribute("listEmployees", employeeService.getAllEmployees());

        return "/views/index";
    }
}
