package kr.co.redbrush.webapp.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupForm {
    @NotEmpty(message = "{validation.id.NotEmpty}")
    @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$", message = "{validation.id.Pattern}")
    private String id;

    @NotEmpty(message = "{validation.email.NotEmpty}")
    @Email(message = "{validation.email.Email}")
    private String email;

    @NotEmpty(message = "{validation.password.NotEmpty}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$", message = "{validation.id.Pattern}")
    private String password;
}
