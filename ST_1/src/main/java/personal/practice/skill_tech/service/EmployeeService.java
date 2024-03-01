package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Employee;
import personal.practice.skill_tech.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {

        this.employeeRepository.save(employee);
    }


    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        Employee employee = null;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }

        return employee;
    }

    public void deleteEmployeeById(long id) {

        this.employeeRepository.deleteById(id);
    }

}
