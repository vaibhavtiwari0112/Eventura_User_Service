package com.example.eventura.userService.service;

import com.example.eventura.userService.dto.LoginRequest;
import com.example.eventura.userService.dto.RegisterRequest;
import com.example.eventura.userService.dto.UserResponse;
import com.example.eventura.userService.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserResponse register(RegisterRequest request);
    Optional<com.example.eventura.userService.entity.User> validateUser(LoginRequest request);
    Optional<UserResponse> getUserById(UUID id);
}
