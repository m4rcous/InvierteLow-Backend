package com.example.inviertelow.platform.letra.application.queryservices;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.queries.GetAllLetrasQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.GetLetraByIdQuery;
import com.example.inviertelow.platform.letra.domain.services.LetraQueryService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.LetraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LetraQueryServiceImpl implements LetraQueryService {

    private final LetraRepository letraRepository;

    public LetraQueryServiceImpl(LetraRepository letraRepository) {
        this.letraRepository = letraRepository;
    }

    @Override
    public List<Letra> handle(GetAllLetrasQuery query) {
        return letraRepository.findAll();
    }

    @Override
    public Optional<Letra> handle(GetLetraByIdQuery query) {
        return letraRepository.findById(query.letraId());
    }
}
