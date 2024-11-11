package com.example.inviertelow.platform.letra.application.commandservices;

import com.example.inviertelow.platform.letra.domain.model.aggregates.CarteraLetra;
import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.AddLetraToCarteraCommand;
import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.CreateCarteraLetraCommand;
import com.example.inviertelow.platform.letra.domain.services.carteraLetra.CarteraLetraCommandService;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.CarteraLetraRepository;
import com.example.inviertelow.platform.letra.infrastructure.persistence.jpa.LetraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarteraLetraCommandServiceImpl implements CarteraLetraCommandService {

    private final CarteraLetraRepository carteraLetraRepository;
    private final LetraRepository letraRepository;

    public CarteraLetraCommandServiceImpl(CarteraLetraRepository carteraLetraRepository, LetraRepository letraRepository) {
        this.carteraLetraRepository = carteraLetraRepository;
        this.letraRepository = letraRepository;
    }

    @Override
    @Transactional
    public CarteraLetra handle(CreateCarteraLetraCommand command) {
        CarteraLetra carteraLetra = new CarteraLetra();
        return carteraLetraRepository.save(carteraLetra);
    }

    @Override
    @Transactional
    public void handle(AddLetraToCarteraCommand command) {
        CarteraLetra cartera = carteraLetraRepository.findById(command.carteraLetraId())
                .orElseThrow(() -> new IllegalArgumentException("Cartera no encontrada"));

        Letra letra = letraRepository.findById(command.letraId())
                .orElseThrow(() -> new IllegalArgumentException("Letra no encontrada"));

        cartera.agregarLetra(letra);
        cartera.calcularCartera();
        carteraLetraRepository.save(cartera);
    }
}
