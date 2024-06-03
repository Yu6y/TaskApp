package com.example.TaskApp.repository;

import com.example.TaskApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String mail);
    Optional<User> findByUsername(String username);
}