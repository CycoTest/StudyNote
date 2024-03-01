package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Employee;
import personal.practice.skill_tech.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // CRUD - read
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    // CRUD - save
    public void saveEmployee(Employee employee) {

        this.employeeRepository.save(employee);
    }

    // CRUD - update
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

    // CRUD - delete
    public void deleteEmployeeById(long id) {

        this.employeeRepository.deleteById(id);
    }

    // pagination for Employee List Board
    public Page<Employee> findPaginated(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return this.employeeRepository.findAll(pageable);
    }
}
