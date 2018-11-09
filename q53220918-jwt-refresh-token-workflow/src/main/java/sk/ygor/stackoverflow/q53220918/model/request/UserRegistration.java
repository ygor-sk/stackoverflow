package sk.ygor.stackoverflow.q53220918.model.request;

import sk.ygor.stackoverflow.q53220918.model.validation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegistration {

    @Email
    private final String email;

    @NotBlank
    private final String name;

    @NotBlank
    @ValidPassword
    private final String password;

    public UserRegistration(@Email String email, @NotBlank String name, @NotBlank String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
