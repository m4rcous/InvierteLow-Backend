package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CostoResource;

public class CostoResourceFromEntityAssembler {
    public static CostoResource toResourceFromEntity(Costo entity) {
        return new CostoResource(
                entity.getMotivo(),
                entity.getMonto(),
                entity.getTipoCosto()
        );
    }
}
