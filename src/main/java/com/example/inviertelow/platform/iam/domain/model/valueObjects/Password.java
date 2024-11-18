package com.example.inviertelow.platform.iam.domain.model.valueObjects;

import com.example.inviertelow.platform.iam.domain.services.HashingService;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Password {

    @Column(nullable = false)
    private String hashedValue;

    protected Password() {
        // Constructor protegido para JPA
    }

    public Password(String rawPassword, HashingService hashingService) {
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.hashedValue = hashingService.hash(rawPassword);
    }

    public boolean matches(String rawPassword, HashingService hashingService) {
        return hashingService.matches(rawPassword, this.hashedValue);
    }

    public String getHashedValue() {
        return hashedValue;
    }
}
