package com.example.inviertelow.platform.letra.domain.services.calculoCarteraLetra;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.calculoCarteraLetra.GetCalculoByCarteraLetraIdQuery;

import java.util.Optional;

public interface CalculoCarteraLetraQueryService {
    Optional<CalculoCarteraLetra> handle(GetCalculoByCarteraLetraIdQuery query);
}
