package com.example.inviertelow.platform.letra.domain.model.valueObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
@Embeddable
public class PlazoDeTasa {
    public enum Plazo {
        DIARIO,
        SEMANAL,
        MENSUAL,
        BIMESTRAL,
        TRIMESTRAL,
        SEMESTRAL,
        ANUAL
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "plazo_de_tasa")
    private Plazo plazo;

    protected PlazoDeTasa() {
    }

    public PlazoDeTasa(Plazo plazo) {
        if (plazo == null) {
            throw new IllegalArgumentException("El plazo de tasa no puede ser nulo");
        }
        this.plazo = plazo;
    }
}
