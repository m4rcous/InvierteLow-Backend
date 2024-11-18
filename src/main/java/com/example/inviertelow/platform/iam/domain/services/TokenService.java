package com.example.inviertelow.platform.iam.domain.services;

import java.util.Map;

public interface TokenService {
    String generateToken(Map<String, Object> claims);
    Map<String, Object> validateToken(String token);
    String extractSubject(String token);
}