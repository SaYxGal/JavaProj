package com.labwork01.app.user.controller;

import com.labwork01.app.configuration.OpenAPI30Configuration;
import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;
import com.labwork01.app.user.service.UserService;
import com.labwork01.app.util.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;


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
    public Pair<Page<UserDto>, List<Integer>> getUsers(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "5") int size){
        final Page<UserDto> users = userService.findAllPages(page, size).map(UserDto::new);
        final int totalPages = users.getTotalPages();
        final List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .toList();
        return Pair.of(users, pageNumbers);
    }
    @PostMapping(URL_LOGIN)
    public UserInfoDto login(@RequestBody @Valid UserLoginDto userLoginDto) {
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
    @GetMapping(URL_MAIN + "/{login}")
    public UserDetails getCurrentUser(@PathVariable String login){
        try{
            return userService.loadUserByUsername(login);
        }
        catch(Exception e){
            return null;
        }
    }
}
