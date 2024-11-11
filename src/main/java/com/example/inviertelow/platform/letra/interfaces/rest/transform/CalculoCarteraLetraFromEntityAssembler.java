package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CalculoCarteraLetraResource;

public class CalculoCarteraLetraFromEntityAssembler {
    public static CalculoCarteraLetraResource toResourceFromEntity(CalculoCarteraLetra entity) {
        return new CalculoCarteraLetraResource(
                entity.getId(),
                entity.getValorTotalRecibido(),
                entity.getTceaCartera()
        );
    }
}
