package com.example.inviertelow.platform.letra.application.commandservices;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.commands.calculoCarteraLetra.UpdateCalculoCarteraLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.entities.CalculoCarteraLetra;
import com.example.inviertelow.platform.letra.domain.services.calculoCarteraLetra.CalculoCarteraLetraCommandService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CalculoCarteraLetraRepository;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CarteraLetraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculoCarteraLetraCommandServiceImpl implements CalculoCarteraLetraCommandService {

    private final CalculoCarteraLetraRepository calculoCarteraLetraRepository;
    private final CarteraLetraRepository carteraLetraRepository;

    public CalculoCarteraLetraCommandServiceImpl(CalculoCarteraLetraRepository calculoCarteraLetraRepository, CarteraLetraRepository carteraLetraRepository) {
        this.calculoCarteraLetraRepository = calculoCarteraLetraRepository;
        this.carteraLetraRepository = carteraLetraRepository;
    }

    @Override
    @Transactional
    public void handle(UpdateCalculoCarteraLetraCommand command) {
        CarteraLetra cartera = carteraLetraRepository.findById(command.carteraLetraId())
                .orElseThrow(() -> new IllegalArgumentException("Cartera no encontrada"));

        if (cartera.getCalculoCarteraLetra() == null) {
            CalculoCarteraLetra calculo = new CalculoCarteraLetra(cartera);
            cartera.setCalculoCarteraLetra(calculo);
            calculoCarteraLetraRepository.save(calculo);
        } else {
            cartera.getCalculoCarteraLetra().recalcular();
            calculoCarteraLetraRepository.save(cartera.getCalculoCarteraLetra());
        }
    }
}
