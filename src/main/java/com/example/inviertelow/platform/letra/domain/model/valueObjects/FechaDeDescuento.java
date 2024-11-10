package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class FechaDeDescuento {
    private LocalDate fecha;

    protected FechaDeDescuento() {
    }

    public FechaDeDescuento(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de descuento no puede ser nula");
        }
        this.fecha = fecha;
    }
}