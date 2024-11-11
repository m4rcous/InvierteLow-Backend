package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.application.commandservices.CarteraLetraCommandServiceImpl;
import com.example.inviertelow.platform.letra.application.queryservices.CarteraLetraQueryServiceImpl;
import com.example.inviertelow.platform.letra.domain.model.commands.carteraLetra.AddLetraToCarteraCommand;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetAllCarterasLetraQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.carteraLetra.GetCarteraLetraByIdQuery;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.AddLetraToCarteraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CarteraLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CreateCarteraLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CarteraLetraResourceFromEntityAssembler;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CreateCarteraLetraCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/cartera-letras", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Cartera Letras", description = "Gestión de carteras de letras, incluyendo la creación y la adición de letras")
public class CarteraLetraController {

    private final CarteraLetraCommandServiceImpl commandService;
    private final CarteraLetraQueryServiceImpl queryService;

    public CarteraLetraController(CarteraLetraCommandServiceImpl commandService, CarteraLetraQueryServiceImpl queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Crea una nueva Cartera de Letras", description = "Inicializa una cartera de letras para agrupar y gestionar múltiples letras financieras.")
    public CarteraLetraResource createCarteraLetra(@RequestBody CreateCarteraLetraResource resource) {
        var command = CreateCarteraLetraCommandFromResourceAssembler.toCommandFromResource(resource);
        var carteraLetra = commandService.handle(command);
        return CarteraLetraResourceFromEntityAssembler.toResourceFromEntity(carteraLetra);
    }

    @PostMapping("/{id}/letras")
    @Operation(summary = "Agrega una letra a la Cartera de Letras", description = "Asocia una letra financiera específica a una cartera de letras existente.")
    public CarteraLetraResource addLetraToCartera(@PathVariable Long id, @RequestBody AddLetraToCarteraResource resource) {
        var command = new AddLetraToCarteraCommand(id, resource.letraId());
        commandService.handle(command);

        var query = new GetCarteraLetraByIdQuery(id);
        var carteraLetra = queryService.handle(query).orElseThrow(() -> new RuntimeException("Cartera de Letras no encontrada"));
        return CarteraLetraResourceFromEntityAssembler.toResourceFromEntity(carteraLetra);
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las Cartera de Letras", description = "Recupera todas las carteras de letras en el sistema.")
    public List<CarteraLetraResource> getAllCarterasLetra() {
        var query = new GetAllCarterasLetraQuery();
        return queryService.handle(query)
                .stream()
                .map(CarteraLetraResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una Cartera de Letras por ID", description = "Obtiene los detalles de una cartera de letras específica utilizando su ID.")
    public CarteraLetraResource getCarteraLetraById(@PathVariable Long id) {
        var query = new GetCarteraLetraByIdQuery(id);
        var carteraLetra = queryService.handle(query).orElseThrow(() -> new RuntimeException("Cartera de Letras no encontrada"));
        return CarteraLetraResourceFromEntityAssembler.toResourceFromEntity(carteraLetra);
    }
}
