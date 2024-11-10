package com.example.inviertelow.platform.letra.interfaces.rest.resources;

import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.domain.model.valueObjects.Monto;

public record CostoResource(
        String motivo,
        Monto monto,
        Costo.TipoCosto tipoCosto
) {
}
