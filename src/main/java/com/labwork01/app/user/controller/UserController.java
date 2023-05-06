package com.labwork01.app.user.controller;

import com.labwork01.app.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public static final String URL_LOGIN = "/jwt/login";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(URL_LOGIN)
    public String login(@RequestBody @Valid UserDto userDto) {
        return userService.loginAndGetToken(userDto);
    }
}
