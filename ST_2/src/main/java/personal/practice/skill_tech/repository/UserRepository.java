package personal.practice.skill_tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.practice.skill_tech.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
