package com.example.inviertelow.platform.letra.interfaces.rest.resources;

import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.*;

import java.util.List;

public record CreateLetraResource(
        Fecha fechaGiro,
        Fecha fechaVencimiento,
        Monto valorNominal,
        Monto retencion,
        Tasa tasa,
        TipoDeTasa tipoDeTasa,
        DiasPorAnio diasPorAnio,
        PlazoDeTasa plazoDeTasa,
        FechaDeDescuento fechaDeDescuento,
        List<CostoResource> costos
) {
}
