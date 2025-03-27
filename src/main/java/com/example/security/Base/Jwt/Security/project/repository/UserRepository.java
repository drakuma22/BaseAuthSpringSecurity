package com.example.security.Base.Jwt.Security.project.repository;

import com.example.security.Base.Jwt.Security.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndEmail(String username, String email);
}
