package com.example.inviertelow.platform.letra.application.commandservices;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.letra.CreateLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.entities.CalculoLetra;
import com.example.inviertelow.platform.letra.domain.services.letra.LetraCommandService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CalculoLetraRepository;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.LetraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LetraCommandServiceImpl implements LetraCommandService {

    private final LetraRepository letraRepository;
    private final CalculoLetraRepository calculoLetraRepository;

    public LetraCommandServiceImpl(LetraRepository letraRepository, CalculoLetraRepository calculoLetraRepository) {
        this.letraRepository = letraRepository;
        this.calculoLetraRepository = calculoLetraRepository;
    }

    @Override
    @Transactional
    public Letra handle(CreateLetraCommand command) {
        Letra nuevaLetra = new Letra(
                command.fechaGiro(),
                command.fechaVencimiento(),
                command.valorNominal(),
                command.retencion(),
                command.tasa(),
                command.tipoDeTasa(),
                command.diasPorAnio(),
                command.plazoDeTasa(),
                command.fechaDeDescuento()
        );

        command.costos().forEach(nuevaLetra::agregarCosto);
        nuevaLetra = letraRepository.save(nuevaLetra);
        CalculoLetra calculoLetra = new CalculoLetra(nuevaLetra);
        calculoLetraRepository.save(calculoLetra);

        return nuevaLetra;
    }
}
