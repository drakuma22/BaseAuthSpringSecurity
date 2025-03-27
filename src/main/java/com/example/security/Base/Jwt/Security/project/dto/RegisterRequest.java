package com.example.security.Base.Jwt.Security.project.dto;

import com.example.security.Base.Jwt.Security.project.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
