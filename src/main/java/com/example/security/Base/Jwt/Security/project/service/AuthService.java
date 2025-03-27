package com.example.security.Base.Jwt.Security.project.service;

import com.example.security.Base.Jwt.Security.project.dto.AuthRequest;
import com.example.security.Base.Jwt.Security.project.dto.AuthResponse;
import com.example.security.Base.Jwt.Security.project.dto.RegisterRequest;
import com.example.security.Base.Jwt.Security.project.model.User;
import com.example.security.Base.Jwt.Security.project.repository.UserRepository;
import com.example.security.Base.Jwt.Security.project.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest req) {
        var user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .email(req.getEmail())
                .role(req.getRole())
                .build();
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest req) {
        var user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities("ROLE_" + user.getRole().name())
                        .build(),
                Map.of("role", user.getRole().name())
        );

        return new AuthResponse(token);
    }
}
