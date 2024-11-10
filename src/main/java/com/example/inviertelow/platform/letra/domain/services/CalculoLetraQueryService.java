package com.example.inviertelow.platform.letra.domain.services;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.GetCalculoByLetraIdQuery;

import java.util.Optional;

public interface CalculoLetraQueryService {
    Optional<CalculoLetra> handle(GetCalculoByLetraIdQuery query);
}
