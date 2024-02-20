package com.app.demo.dto;

import com.app.demo.entities.User;
import lombok.Data;

@Data
public class AuthDto {
    private String accessToken;
    private String tokenType;

    public AuthDto(String accessToken) {
        this.accessToken = accessToken;
        tokenType = "Bearer ";
    }
}
