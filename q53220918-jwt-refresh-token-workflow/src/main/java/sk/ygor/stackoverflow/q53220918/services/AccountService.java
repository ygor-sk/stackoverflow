package sk.ygor.stackoverflow.q53220918.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.ygor.stackoverflow.q53220918.domain.User;
import sk.ygor.stackoverflow.q53220918.domain.UserRefreshToken;
import sk.ygor.stackoverflow.q53220918.model.exception.UserNotFoundException;
import sk.ygor.stackoverflow.q53220918.model.exception.WrongPasswordException;
import sk.ygor.stackoverflow.q53220918.model.request.UserLogin;
import sk.ygor.stackoverflow.q53220918.model.response.AccessToken;
import sk.ygor.stackoverflow.q53220918.model.response.TokenPair;
import sk.ygor.stackoverflow.q53220918.repository.UserRefreshTokenRepository;
import sk.ygor.stackoverflow.q53220918.repository.UserRepository;
import sk.ygor.stackoverflow.q53220918.security.JWTAuthorizationFilter;

import java.util.Optional;

@Service
public class AccountService {

    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public AccountService(UserRefreshTokenRepository userRefreshTokenRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JWTAuthorizationFilter jwtAuthorizationFilter) {
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    public TokenPair doLoginUser(User user) {
        String jwt = jwtAuthorizationFilter.generateAccessToken(user.getId());
        String refreshToken = createRefreshToken(user);
        return new TokenPair(jwt, refreshToken);
    }

    /**
     * @return newly generated access token or nothing, if the refresh token is not valid
     */
    public Optional<AccessToken> refreshAccessToken(String refreshToken) {
        return userRefreshTokenRepository.findByToken(refreshToken)
                .map(userRefreshToken -> new AccessToken(
                        jwtAuthorizationFilter.generateAccessToken(userRefreshToken.getUser().getId())
                ));
    }

    private String createRefreshToken(User user) {
        String token = RandomStringUtils.randomAlphanumeric(128);
        userRefreshTokenRepository.save(new UserRefreshToken(token, user));
        return token;

    }

    public TokenPair loginUser(UserLogin userLogin) {
        return userRepository.findByEmail(userLogin.getEmail())
                .map(user -> {
                    if (passwordEncoder.matches(userLogin.getPassword(), user.getPassword())) {
                        return doLoginUser(user);
                    } else {
                        throw new WrongPasswordException();
                    }
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public void logoutUser(String refreshToken) {
        userRefreshTokenRepository.findByToken(refreshToken)
                .ifPresent(userRefreshTokenRepository::delete);
    }
}
