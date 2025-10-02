package com.example.eventura.userService.controller;

import com.example.eventura.userService.dto.LoginRequest;
import com.example.eventura.userService.dto.LoginResponse;
import com.example.eventura.userService.dto.RegisterRequest;
import com.example.eventura.userService.dto.UserResponse;
import com.example.eventura.userService.entity.User;
import com.example.eventura.userService.security.JwtUtil;
import com.example.eventura.userService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("inside the login controller --> " + request);

        Optional<User> user = userService.validateUser(request);

        if (user.isPresent()) {
            System.out.println(">>>>>>  " + user);
            String token = jwtUtil.generateToken(
                    user.get().getUsername(),
                    user.get().getRole()
            );

            return ResponseEntity.ok(
                    new LoginResponse(
                            token,
                            user.get().getUsername(),
                            user.get().getEmail(),
                            user.get().getRole(),
                            user.get().getId()
                    )
            );
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
