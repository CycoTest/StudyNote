package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Employee;
import personal.practice.skill_tech.repository.EmployeeRepository;

import java.util.List;

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



}
