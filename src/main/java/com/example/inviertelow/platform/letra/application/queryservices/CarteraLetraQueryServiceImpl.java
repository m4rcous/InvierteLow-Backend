package com.example.inviertelow.platform.letra.application.queryservices;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetAllCarterasLetraQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetCarteraLetraByIdQuery;
import com.example.inviertelow.platform.letra.domain.services.carteraLetra.CarteraLetraQueryService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CarteraLetraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteraLetraQueryServiceImpl implements CarteraLetraQueryService {

    private final CarteraLetraRepository carteraLetraRepository;

    public CarteraLetraQueryServiceImpl(CarteraLetraRepository carteraLetraRepository) {
        this.carteraLetraRepository = carteraLetraRepository;
    }

    @Override
    public List<CarteraLetra> handle(GetAllCarterasLetraQuery query) {
        return carteraLetraRepository.findAll();
    }

    @Override
    public Optional<CarteraLetra> handle(GetCarteraLetraByIdQuery query) {
        return carteraLetraRepository.findById(query.carteraLetraId());
    }
}
