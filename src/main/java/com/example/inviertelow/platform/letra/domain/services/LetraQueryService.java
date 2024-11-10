package com.example.inviertelow.platform.letra.domain.services;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.queries.GetAllLetrasQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.GetLetraByIdQuery;

import java.util.List;
import java.util.Optional;

public interface LetraQueryService {
    List<Letra> handle(GetAllLetrasQuery query);
    Optional<Letra> handle(GetLetraByIdQuery query);
}
