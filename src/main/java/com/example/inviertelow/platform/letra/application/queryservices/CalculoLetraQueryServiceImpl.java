package com.example.inviertelow.platform.letra.application.queryservices;

import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import com.example.inviertelow.platform.letra.domain.model.queries.GetCalculoByLetraIdQuery;
import com.example.inviertelow.platform.letra.domain.services.CalculoLetraQueryService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CalculoLetraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalculoLetraQueryServiceImpl implements CalculoLetraQueryService {

    private final CalculoLetraRepository calculoLetraRepository;

    public CalculoLetraQueryServiceImpl(CalculoLetraRepository calculoLetraRepository) {
        this.calculoLetraRepository = calculoLetraRepository;
    }

    @Override
    public Optional<CalculoLetra> handle(GetCalculoByLetraIdQuery query) {
        return calculoLetraRepository.findByLetraId(query.letraId());
    }
}
