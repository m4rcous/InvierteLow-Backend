package com.example.inviertelow.platform.letra.domain.services.carteraLetra;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetAllCarterasLetraQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetCarteraLetraByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CarteraLetraQueryService {
    List<CarteraLetra> handle(GetAllCarterasLetraQuery query);
    Optional<CarteraLetra> handle(GetCarteraLetraByIdQuery query);
}
