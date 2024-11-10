package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CostoResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.LetraResource;

import java.util.List;
import java.util.stream.Collectors;

public class LetraResourceFromEntityAssembler {
    public static LetraResource toResourceFromEntity(Letra entity) {
        List<CostoResource> costoResources = entity.getCostos().stream()
                .map(CostoResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return new LetraResource(
                entity.getId(),
                entity.getFechaGiro(),
                entity.getFechaVencimiento(),
                entity.getValorNominal(),
                entity.getRetencion(),
                entity.getTasa(),
                entity.getTipoDeTasa(),
                entity.getDiasPorAnio(),
                entity.getPlazoDeTasa(),
                entity.getFechaDeDescuento(),
                costoResources
        );
    }
}
