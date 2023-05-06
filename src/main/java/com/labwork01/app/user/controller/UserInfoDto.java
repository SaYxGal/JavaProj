package com.labwork01.app.user.controller;

import com.labwork01.app.user.model.UserRole;

public class UserInfoDto {
    private final String token;
    private final String login;
    private final UserRole role;
    public UserInfoDto(String token, String login, UserRole role) {
        this.token = token;
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public UserRole getRole() {
        return role;
    }
}
