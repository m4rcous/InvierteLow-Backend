package com.example.inviertelow.platform.letra.interfaces.rest.resources;

import java.math.BigDecimal;

public record CalculoCarteraLetraResource(
        Long id,
        BigDecimal valorTotalRecibido,
        BigDecimal tceaCartera
) {}
