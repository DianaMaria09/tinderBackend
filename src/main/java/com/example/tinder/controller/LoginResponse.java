package com.example.tinder.controller;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;


public class LoginResponse {

    private Long userId;

    private String token;

    public LoginResponse(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
