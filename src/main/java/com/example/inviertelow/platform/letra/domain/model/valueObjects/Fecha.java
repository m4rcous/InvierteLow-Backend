package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class Fecha {
    private LocalDate fecha;

    protected Fecha() {
    }

    public Fecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        this.fecha = fecha;
    }
}
