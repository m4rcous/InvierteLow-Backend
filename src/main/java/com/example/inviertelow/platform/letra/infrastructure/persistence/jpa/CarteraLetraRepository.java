package com.example.inviertelow.platform.letra.infrastructure.persistence.jpa;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteraLetraRepository extends JpaRepository<CarteraLetra, Long> {
}
