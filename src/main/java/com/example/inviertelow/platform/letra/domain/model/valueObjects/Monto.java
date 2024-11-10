package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Embeddable
public class Monto {
    private BigDecimal valor;

    protected  Monto() {
    }

    public Monto(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El monto no puede ser nulo o negativo");
        }
        this.valor = valor;
    }
}
