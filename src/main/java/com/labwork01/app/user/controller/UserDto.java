package com.labwork01.app.user.controller;

import com.labwork01.app.user.model.User;
import com.labwork01.app.user.model.UserRole;

public class UserDto {
    private final long id;
    private final String login;
    private final String password;
    private final UserRole role;

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getRole() {
        return role;
    }
    public String getPassword(){
        return password;
    }
}
