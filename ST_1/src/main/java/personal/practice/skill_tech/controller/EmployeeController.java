package personal.practice.skill_tech.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import personal.practice.skill_tech.model.Employee;
import personal.practice.skill_tech.service.EmployeeService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // display list of employees
    @GetMapping("/")
    public String viewHome(Model model) {
        // default pageNo = 1
        return findPaginated(1, "firstName", "asc", model);

//        return "/views/index";
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

    // /page/1?sortField=name&sortDir=asc
    @GetMapping("/page/{pageNo}") // pagination
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService .findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);

        return "/views/index";
    }

}
