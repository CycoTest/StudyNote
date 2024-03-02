package personal.practice.skill_tech.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegirstDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
