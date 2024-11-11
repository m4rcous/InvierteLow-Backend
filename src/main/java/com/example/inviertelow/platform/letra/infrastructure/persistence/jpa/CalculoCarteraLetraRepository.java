package com.example.inviertelow.platform.letra.infrastructure.persistence.jpa;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalculoCarteraLetraRepository extends JpaRepository<CalculoCarteraLetra, Long> {
    Optional<CalculoCarteraLetra> findByCarteraLetraId(Long carteraLetraId);
}
