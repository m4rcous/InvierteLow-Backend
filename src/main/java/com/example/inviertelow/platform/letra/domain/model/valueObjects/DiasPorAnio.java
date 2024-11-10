package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DiasPorAnio {
    private int dias;

    protected  DiasPorAnio() {
    }

    public DiasPorAnio(int dias) {
        if (dias != 360 && dias != 365) {
            throw new IllegalArgumentException("Los días por año deben ser 360 o 365");
        }
        this.dias = dias;
    }
}
