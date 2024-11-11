package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.commands.letra.CreateLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.entities.Costo;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CostoResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CreateLetraResource;

import java.util.List;
import java.util.stream.Collectors;

public class CreateLetraCommandFromResourceAssembler {
    public static CreateLetraCommand toCommandFromResource(CreateLetraResource resource) {
        List<Costo> costos = resource.costos().stream()
                .map(CreateLetraCommandFromResourceAssembler::toCostoFromResource)
                .collect(Collectors.toList());

        return new CreateLetraCommand(
                resource.fechaGiro(),
                resource.fechaVencimiento(),
                resource.valorNominal(),
                resource.retencion(),
                resource.tasa(),
                resource.tipoDeTasa(),
                resource.diasPorAnio(),
                resource.plazoDeTasa(),
                resource.fechaDeDescuento(),
                costos
        );
    }

    private static Costo toCostoFromResource(CostoResource resource) {
        return new Costo(
                resource.motivo(),
                resource.monto(),
                resource.tipoCosto()
        );
    }
}
