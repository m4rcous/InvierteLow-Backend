package com.example.inviertelow.platform.letra.interfaces.rest.resources;

import java.math.BigDecimal;

public record CalculoLetraResource(
        Long id,
        int numeroDiasTranscurridos,
        BigDecimal tasaEfectivaPeriodo,
        BigDecimal tasaDescontadaPeriodo,
        BigDecimal descuento,
        BigDecimal valorNeto,
        BigDecimal valorRecibido,
        BigDecimal valorEntregado,
        BigDecimal tcea
) {
}
