package com.labwork01.app.user.controller;

import com.labwork01.app.user.model.UserRole;

public class UserInfoDto {
    private final String token;
    private final UserRole role;
    public UserInfoDto(String token, UserRole role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public UserRole getRole() {
        return role;
    }
}
