package com.example.inviertelow.platform.iam.domain.model.entities;

import com.example.inviertelow.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends AuditableModel {

    @Column(unique = true, nullable = false)
    private String name;

    protected Role() {
        // Constructor protegido para JPA
    }

    public Role(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        this.name = name;
    }
}
