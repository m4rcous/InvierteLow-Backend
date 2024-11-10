package com.example.inviertelow.platform.letra.infrastructure.persistence.jpa;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LetraRepository extends JpaRepository<Letra, Long> {
    Optional<Letra> findLetraById(Long Id);
}
