package com.example.security.Base.Jwt.Security.project.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
