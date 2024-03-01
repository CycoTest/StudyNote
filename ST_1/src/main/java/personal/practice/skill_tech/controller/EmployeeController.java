package personal.practice.skill_tech.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.practice.skill_tech.model.Employee;
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

    @GetMapping("/showNewEmployeeForm") // CRUD - read
    public String showNewEmployeeForm(Model model) {

        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "/views/base/newEmployee";
    }

    @PostMapping("/saveEmployee") // CRUD - create
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        // save employee to database
        employeeService.saveEmployee(employee);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}") // CRUD - update
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);

        return "/views/base/updateEmployee";
    }

    @GetMapping("/deleteEmployee/{id}") // CRUD - delete
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);

        return "redirect:/";
    }

}
