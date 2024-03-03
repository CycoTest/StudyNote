package personal.practice.skill_tech.web;

import jakarta.annotation.PostConstruct;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.practice.skill_tech.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>();

    @PostConstruct
    private void init() {
        Student student1 = new Student();
        student1.setId(Long.valueOf(1));
        student1.setFirstName("John");
        student1.setLastName("Do");

        Student student2 = new Student();
        student2.setId(Long.valueOf(2));
        student2.setFirstName("Mary");
        student2.setLastName("Jaine");

        students.add(student1);
        students.add(student2);
    }

    @GetMapping("/students")
    public List<Student> students() {

        return students;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        students.add(student);

        return student;
    }
}
