package com.example.inviertelow.platform.letra.application.queryservices;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.calculoCarteraLetra.GetCalculoByCarteraLetraIdQuery;
import com.example.inviertelow.platform.letra.domain.services.calculoCarteraLetra.CalculoCarteraLetraQueryService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CalculoCarteraLetraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculoCarteraQueryServiceImpl implements CalculoCarteraLetraQueryService {

    private final CalculoCarteraLetraRepository calculoCarteraLetraRepository;

    public CalculoCarteraQueryServiceImpl(CalculoCarteraLetraRepository calculoCarteraLetraRepository) {
        this.calculoCarteraLetraRepository = calculoCarteraLetraRepository;
    }

    @Override
    public Optional<CalculoCarteraLetra> handle(GetCalculoByCarteraLetraIdQuery query) {
        return calculoCarteraLetraRepository.findByCarteraLetraId(query.carteraLetraId());
    }
}
