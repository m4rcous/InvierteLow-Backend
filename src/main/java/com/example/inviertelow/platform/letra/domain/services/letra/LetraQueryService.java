package com.example.inviertelow.platform.letra.domain.services.letra;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.queries.letra.GetAllLetrasQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.letra.GetLetraByIdQuery;

import java.util.List;
import java.util.Optional;

public interface LetraQueryService {
    List<Letra> handle(GetAllLetrasQuery query);
    Optional<Letra> handle(GetLetraByIdQuery query);
}
