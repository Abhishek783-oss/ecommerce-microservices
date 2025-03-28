package com.abhi.authservice.dto;

import lombok.*;


public class AuthResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponse(String token) {
        this.token = token;
    }

    public AuthResponse() {
    }
}
