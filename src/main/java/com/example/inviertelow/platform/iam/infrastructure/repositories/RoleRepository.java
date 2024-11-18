package com.example.inviertelow.platform.iam.infrastructure.repositories;

import com.example.inviertelow.platform.iam.domain.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
