package com.example.inviertelow.platform.iam.domain.services;

public interface HashingService {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
