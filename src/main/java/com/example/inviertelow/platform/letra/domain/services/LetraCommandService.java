package com.example.inviertelow.platform.letra.domain.services;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.CreateLetraCommand;

public interface LetraCommandService {
    Letra handle(CreateLetraCommand command);
}
