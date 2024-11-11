package com.example.inviertelow.platform.letra.interfaces.rest.transform;

import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.CreateCarteraLetraCommand;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CreateCarteraLetraResource;

public class CreateCarteraLetraCommandFromResourceAssembler {
    public static CreateCarteraLetraCommand toCommandFromResource(CreateCarteraLetraResource resource) {
        return new CreateCarteraLetraCommand();
    }
}
