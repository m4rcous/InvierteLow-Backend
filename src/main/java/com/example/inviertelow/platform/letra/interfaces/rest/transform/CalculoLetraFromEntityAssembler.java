package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CalculoLetraResource;

public class CalculoLetraFromEntityAssembler {
    public static CalculoLetraResource toResourceFromEntity(CalculoLetra entity) {
        return new CalculoLetraResource(
                entity.getId(),
                entity.getNumeroDiasTranscurridos(),
                entity.calcularTasaEfectivaPeriodo(),
                entity.calcularTasaDescontadaPeriodo(),
                entity.calcularDescuento(),
                entity.calcularValorNeto(),
                entity.calcularValorRecibido(),
                entity.calcularValorEntregado(),
                entity.calcularTCEA()
        );
    }
}
