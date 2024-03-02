package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Role;
import personal.practice.skill_tech.model.User;
import personal.practice.skill_tech.repository.UserRepository;
import personal.practice.skill_tech.web.dto.UserRegirstDto;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(UserRegirstDto regirstDto) {
        User user = new User(regirstDto.getFirstName(), regirstDto.getLastName(),
                regirstDto.getEmail(), regirstDto.getPassword(), Arrays.asList(new Role("Role_User")));

        return userRepository.save(user);
    }
}
