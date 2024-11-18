package com.example.inviertelow.platform.iam.domain.model.aggregates;

import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Password;
import com.example.inviertelow.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AuditableModel {

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    protected User() {
        // Constructor protegido para JPA
    }

    public User(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName) {
        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
}
