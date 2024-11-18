package com.example.inviertelow.platform.iam.domain.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Email {

    @Column(unique = true, nullable = false)
    private String value;

    protected Email() {
        // Constructor protegido para JPA
    }

    public Email(String value) {
        if (!isValidEmail(value)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
