package com.example.inviertelow.platform.letra.domain.model.entities;

import com.example.inviertelow.platform.letra.domain.model.valueObjects.Monto;
import com.example.inviertelow.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "costos")
public class Costo extends AuditableModel {

    public enum TipoCosto {
        INICIAL,
        FINAL
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_costo", nullable = false)
    private TipoCosto tipoCosto;

    @Column(nullable = false)
    private String motivo;

    @Embedded
    private Monto monto;

    public Costo(String motivo, Monto monto, TipoCosto tipoCosto) {
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo no puede ser nulo o vacío");
        }
        if (monto == null) {
            throw new IllegalArgumentException("El monto no puede ser nulo");
        }
        if (tipoCosto == null) {
            throw new IllegalArgumentException("El tipo de costo no puede ser nulo");
        }
        this.motivo = motivo;
        this.monto = monto;
        this.tipoCosto = tipoCosto;
    }

    protected Costo() {
        // Constructor protegido para JPA
    }
}
