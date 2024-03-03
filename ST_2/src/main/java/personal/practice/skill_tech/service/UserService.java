package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Role;
import personal.practice.skill_tech.model.User;
import personal.practice.skill_tech.repository.UserRepository;
import personal.practice.skill_tech.web.dto.UserRegirstDto;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User save(UserRegirstDto regirstDto) {
        User user = new User();
        user.setFirstName(regirstDto.getFirstName());
        user.setLastName(regirstDto.getLastName());
        user.setEmail(regirstDto.getEmail());
        user.setPassword(regirstDto.getPassword());
        user.setRoles(Arrays.asList(new Role("Role_User")));

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email" + username) );

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
