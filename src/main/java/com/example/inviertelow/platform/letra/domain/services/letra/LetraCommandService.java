package com.example.inviertelow.platform.letra.domain.services.letra;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.letra.CreateLetraCommand;

public interface LetraCommandService {
    Letra handle(CreateLetraCommand command);
}
