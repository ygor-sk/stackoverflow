package sk.ygor.stackoverflow.q53220918.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.ygor.stackoverflow.q53220918.domain.User;
import sk.ygor.stackoverflow.q53220918.model.exception.InvalidRefreshTokenException;
import sk.ygor.stackoverflow.q53220918.model.request.RefreshToken;
import sk.ygor.stackoverflow.q53220918.model.request.UserLogin;
import sk.ygor.stackoverflow.q53220918.model.request.UserRegistration;
import sk.ygor.stackoverflow.q53220918.model.response.AccessToken;
import sk.ygor.stackoverflow.q53220918.model.response.TokenPair;
import sk.ygor.stackoverflow.q53220918.model.response.UserInfo;
import sk.ygor.stackoverflow.q53220918.security.SecurityService;
import sk.ygor.stackoverflow.q53220918.services.AccountService;
import sk.ygor.stackoverflow.q53220918.services.UserService;

import javax.validation.Valid;

@RestController
public class AccountController {

    public static final String PATH_POST_REFRESH = "/account/token/refresh";
    public static final String PATH_POST_LOGIN = "/account/login";
    public static final String PATH_POST_SIGN_UP = "/account/register";
    private static final String PATH_DELETE_LOGOUT = "/account/logout";
    private static final String PATH_GET_ME = "/account/me";

    private final AccountService accountService;
    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public AccountController(AccountService accountService,
                             UserService userService,
                             SecurityService securityService) {
        this.accountService = accountService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping(path = AccountController.PATH_POST_SIGN_UP, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody TokenPair registerUser(@Valid @RequestBody UserRegistration userRegistration) {
        User user = userService.registerUser(userRegistration);
        return accountService.doLoginUser(user);
    }

    @RequestMapping(path = PATH_POST_REFRESH, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody AccessToken tokenPostRefresh(@Valid @RequestBody RefreshToken refreshToken) {
        return accountService.refreshAccessToken(refreshToken.getRefreshToken())
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @RequestMapping(path = PATH_POST_LOGIN, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody TokenPair tokenPostLogin(@Valid @RequestBody UserLogin userLogin) {
        return accountService.loginUser(userLogin);
    }

    @RequestMapping(path = PATH_DELETE_LOGOUT, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void tokenDeleteLogout(@Valid @RequestBody RefreshToken refreshToken) {
        accountService.logoutUser(refreshToken.getRefreshToken());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = PATH_GET_ME, method = RequestMethod.GET)
    public @ResponseBody UserInfo tokenGetMe() {
        Long userId = securityService.getLoggedUserId();
        return userService.getUserInfo(userId);
    }

}
