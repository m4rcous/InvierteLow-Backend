package com.example.inviertelow.platform.letra.infrastructure.persistence.jpa;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculoLetraRepository extends JpaRepository<CalculoLetra, Long> {
    Optional<CalculoLetra> findByLetraId(Long letraId);
}
