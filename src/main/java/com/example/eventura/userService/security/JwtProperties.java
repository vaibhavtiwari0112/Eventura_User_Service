package com.example.eventura.userService.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "eventura.security.jwt")
public class JwtProperties {
    private String secret;
    private String issuer;
    private long expiration; // in ms
}
