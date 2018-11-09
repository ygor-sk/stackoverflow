package sk.ygor.stackoverflow.q53220918.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.ygor.stackoverflow.q53220918.domain.User;
import sk.ygor.stackoverflow.q53220918.model.exception.DuplicateEmailException;
import sk.ygor.stackoverflow.q53220918.model.request.UserRegistration;
import sk.ygor.stackoverflow.q53220918.model.response.UserInfo;
import sk.ygor.stackoverflow.q53220918.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserRegistration userRegistration) {
        Optional<User> existingUser = userRepository.findByEmail(userRegistration.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateEmailException();
        }

        User user = new User(
                userRegistration.getEmail(),
                userRegistration.getName(),
                passwordEncoder.encode(userRegistration.getPassword())
        );
        userRepository.save(user);
        return user;
    }

    public UserInfo getUserInfo(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserInfo(user.getEmail(), user.getName()))
                .orElseThrow(IllegalStateException::new);
    }

}
