package com.example.inviertelow.platform.iam.infrastructure.repositories;

import com.example.inviertelow.platform.iam.domain.model.aggregates.User;
import com.example.inviertelow.platform.iam.domain.model.valueObjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(Email email);
    boolean existsByEmail(Email email);
}
