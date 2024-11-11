package com.example.inviertelow.platform.letra.domain.services.carteraLetra;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.AddLetraToCarteraCommand;
import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.CreateCarteraLetraCommand;

public interface CarteraLetraCommandService {
    CarteraLetra handle(CreateCarteraLetraCommand command);
    void handle(AddLetraToCarteraCommand command);
}
