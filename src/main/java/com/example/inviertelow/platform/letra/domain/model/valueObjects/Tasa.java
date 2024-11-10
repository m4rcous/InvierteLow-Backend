package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class Tasa {
    private BigDecimal valor;

    protected  Tasa() {
    }

    public Tasa(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa no puede ser negativa");
        }
        this.valor = valor;
    }
}
