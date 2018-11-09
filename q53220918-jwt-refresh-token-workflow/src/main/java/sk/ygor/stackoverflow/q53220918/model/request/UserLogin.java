package sk.ygor.stackoverflow.q53220918.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLogin {

    @Email
    private final String email;

    @NotBlank
    private final String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
