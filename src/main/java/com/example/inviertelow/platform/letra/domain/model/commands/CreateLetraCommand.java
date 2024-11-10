package com.example.inviertelow.platform.letra.domain.model.commands;

import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.*;

import java.util.List;

public record CreateLetraCommand(
        Fecha fechaGiro,
        Fecha fechaVencimiento,
        Monto valorNominal,
        Monto retencion,
        Tasa tasa,
        TipoDeTasa tipoDeTasa,
        DiasPorAnio diasPorAnio,
        PlazoDeTasa plazoDeTasa,
        FechaDeDescuento fechaDeDescuento,
        List<Costo> costos
) {
    public CreateLetraCommand {
        if (fechaGiro == null || fechaVencimiento == null) {
            throw new IllegalArgumentException("Las fechas de giro y vencimiento no pueden ser nulas");
        }
        if (valorNominal == null || retencion == null) {
            throw new IllegalArgumentException("El valor nominal y retención no pueden ser nulos");
        }
        if (tasa == null || tipoDeTasa == null) {
            throw new IllegalArgumentException("La tasa y el tipo de tasa no pueden ser nulos");
        }
        if (diasPorAnio == null || plazoDeTasa == null) {
            throw new IllegalArgumentException("Los días por año y el plazo de tasa no pueden ser nulos");
        }
        if (fechaDeDescuento == null) {
            throw new IllegalArgumentException("La fecha de descuento no puede ser nula");
        }
        if (costos == null) {
            throw new IllegalArgumentException("La lista de costos no puede ser nula");
        }
        if (fechaGiro.getFecha().isAfter(fechaVencimiento.getFecha())) {
            throw new IllegalArgumentException("La fecha de giro debe ser anterior a la fecha de vencimiento");
        }
    }
}
