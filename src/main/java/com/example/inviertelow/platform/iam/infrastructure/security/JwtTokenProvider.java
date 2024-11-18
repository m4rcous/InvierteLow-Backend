package com.example.inviertelow.platform.iam.infrastructure.security;

import com.example.inviertelow.platform.iam.domain.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenService {

    private static final String SECRET_KEY = "rs1gcPnFXnRRtEWaBWTnd6QqcwV67acf8/6yRdG+8Cw="; // Clave secreta, cámbiala por algo más seguro
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    @Override
    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public Map<String, Object> validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return new HashMap<>(claims);
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }

    @Override
    public String extractSubject(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token");
        }
    }
}
