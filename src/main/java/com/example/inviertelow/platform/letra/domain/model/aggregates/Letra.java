package com.example.inviertelow.platform.letra.domain.model.aggregates;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.*;
import com.example.inviertelow.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
public class Letra extends AuditableAbstractAggregateRoot<Letra> {
    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_giro"))
    private Fecha fechaGiro;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_vencimiento"))
    private Fecha fechaVencimiento;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "valor_nominal"))
    private Monto valorNominal;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "valor_retencion"))
    private Monto retencion;

    @Embedded
    private Tasa tasa;

    @Embedded
    private TipoDeTasa tipoDeTasa;

    @Embedded
    private DiasPorAnio diasPorAnio;

    @Embedded
    private PlazoDeTasa plazoDeTasa;

    @Embedded
    @AttributeOverride(name = "fecha", column = @Column(name = "fecha_descuento"))
    private FechaDeDescuento fechaDeDescuento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "letra_id")
    private final List<Costo> costos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "letra", orphanRemoval = true)
    private CalculoLetra calculoLetra;

    public Letra(Fecha fechaGiro, Fecha fechaVencimiento, Monto valorNominal, Monto retencion, Tasa tasa, TipoDeTasa tipoDeTasa, DiasPorAnio diasPorAnio, PlazoDeTasa plazoDeTasa, FechaDeDescuento fechaDeDescuento) {
        if (fechaGiro.getFecha().isAfter(fechaVencimiento.getFecha())) {
            throw new IllegalArgumentException("La fecha de giro debe ser antes de la fecha de vencimiento");
        }
        this.fechaGiro = fechaGiro;
        this.fechaVencimiento = fechaVencimiento;
        this.valorNominal = valorNominal;
        this.retencion = retencion;
        this.tasa = tasa;
        this.tipoDeTasa = tipoDeTasa;
        this.diasPorAnio = diasPorAnio;
        this.plazoDeTasa = plazoDeTasa;
        this.fechaDeDescuento = fechaDeDescuento;
    }

    protected Letra() {
        // Constructor protegido para JPA
    }

    public void agregarCosto(Costo costo) {
        this.costos.add(costo);
    }

    public void eliminarUltimoCostoInicial() {
        List<Costo> costosIniciales = this.costos.stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.INICIAL)
                .collect(Collectors.toList());

        if (!costosIniciales.isEmpty()) {
            this.costos.remove(costosIniciales.get(costosIniciales.size() - 1));
        }
    }

    public void eliminarUltimoCostoFinal() {
        List<Costo> costosFinales = this.costos.stream()
                .filter(costo -> costo.getTipoCosto() == Costo.TipoCosto.FINAL)
                .collect(Collectors.toList());

        if (!costosFinales.isEmpty()) {
            this.costos.remove(costosFinales.get(costosFinales.size() - 1));
        }
    }

    public void generarCalculoLetra() {
        this.calculoLetra = new CalculoLetra(this);
    }
}
