package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class TipoDeTasa {
    public enum Tipo {
        EFECTIVA,
        NOMINAL
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_de_tasa")
    private Tipo tipo;

    protected TipoDeTasa() {
    }

    public TipoDeTasa(Tipo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de tasa no puede ser nulo");
        }
        this.tipo = tipo;
    }
}
