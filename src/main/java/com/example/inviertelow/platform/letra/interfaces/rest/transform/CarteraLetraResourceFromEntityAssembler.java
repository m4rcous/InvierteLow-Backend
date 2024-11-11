package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CarteraLetraResource;

import java.util.List;
import java.util.stream.Collectors;

public class CarteraLetraResourceFromEntityAssembler {
    public static CarteraLetraResource toResourceFromEntity(CarteraLetra entity) {
        List<Long> letrasIds = entity.getLetras().stream()
                .map(letra -> letra.getId())
                .collect(Collectors.toList());

        Long calculoCarteraLetraId = entity.getCalculoCarteraLetra() != null
                ? entity.getCalculoCarteraLetra().getId()
                : null;

        return new CarteraLetraResource(
                entity.getId(),
                letrasIds,
                calculoCarteraLetraId
        );
    }
}
