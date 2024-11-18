package com.example.inviertelow.platform.iam.infrastructure.security;

import com.example.inviertelow.platform.iam.domain.services.HashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptHashingService implements HashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    public BCryptHashingService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
