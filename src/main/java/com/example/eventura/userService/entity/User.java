package com.example.eventura.userService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT gen_random_uuid()")
    private UUID id;


    @Column(unique = true, nullable = false)
    private String username;

    private String email;
    private String password; // bcrypt hashed
    private String role;     // USER / ADMIN
}
