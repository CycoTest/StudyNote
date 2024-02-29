package personal.practice.skill_tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.practice.skill_tech.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
