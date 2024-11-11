package com.example.inviertelow.platform.letra.domain.services.calculoCarteraLetra;

import com.example.inviertelow.platform.letra.domain.model.commands.calculoCarteraLetra.UpdateCalculoCarteraLetraCommand;

public interface CalculoCarteraLetraCommandService {
    void handle(UpdateCalculoCarteraLetraCommand command);
}
