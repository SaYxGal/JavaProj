package com.labwork01.app.user.controller;

import com.labwork01.app.configuration.OpenAPI30Configuration;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;
import com.labwork01.app.user.service.UserService;
import com.labwork01.app.util.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    public static final String URL_LOGIN = "/jwt/login";
    public static final String URL_SIGNUP = "/signup";
    public static final String URL_MAIN = "/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(OpenAPI30Configuration.API_PREFIX + URL_MAIN)
    @Secured({UserRole.AsString.ADMIN})
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size){
        return userService.findAllPages(page, size).map(UserDto::new);
    }
    @PostMapping(URL_LOGIN)
    public String login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return userService.loginAndGetToken(userLoginDto);
    }
    @PostMapping(URL_SIGNUP)
    public String signup(@RequestBody @Valid UserSignupDto userSignupDto){
        try {
            User user = userService.createUser(userSignupDto.getLogin(), userSignupDto.getPassword(), userSignupDto.getPasswordConfirm());
            return user.getLogin() + "was created";
        }
        catch(ValidationException e){
            return e.getMessage();
        }
    }
}
