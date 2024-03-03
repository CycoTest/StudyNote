package personal.practice.skill_tech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import personal.practice.skill_tech.model.Role;
import personal.practice.skill_tech.model.User;
import personal.practice.skill_tech.repository.UserRepository;
import personal.practice.skill_tech.web.dto.UserRegirstDto;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password" + username) );

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
