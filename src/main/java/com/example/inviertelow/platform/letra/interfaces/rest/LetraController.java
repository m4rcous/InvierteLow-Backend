package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.CreateLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.queries.GetAllLetrasQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.GetLetraByIdQuery;
import com.example.inviertelow.platform.letra.domain.services.LetraCommandService;
import com.example.inviertelow.platform.letra.domain.services.LetraQueryService;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CreateLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.LetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CreateLetraCommandFromResourceAssembler;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.LetraResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/letras", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Letras", description = "bla bla bla bla")
public class LetraController {

    private final LetraCommandService letraCommandService;
    private final LetraQueryService letraQueryService;

    public LetraController(LetraCommandService letraCommandService, LetraQueryService letraQueryService) {
        this.letraCommandService = letraCommandService;
        this.letraQueryService = letraQueryService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear una nueva letra")
    public ResponseEntity<LetraResource> createLetra(@RequestBody CreateLetraResource createLetraResource) {
        CreateLetraCommand command = CreateLetraCommandFromResourceAssembler.toCommandFromResource(createLetraResource);
        Letra nuevaLetra = letraCommandService.handle(command);
        LetraResource letraResource = LetraResourceFromEntityAssembler.toResourceFromEntity(nuevaLetra);

        return ResponseEntity.created(URI.create("/api/v1/letras/" + nuevaLetra.getId())).body(letraResource);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las letras")
    public ResponseEntity<List<LetraResource>> getAllLetras() {
        List<Letra> letras = letraQueryService.handle(new GetAllLetrasQuery());
        List<LetraResource> letraResources = letras.stream()
                .map(LetraResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(letraResources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una letra por ID")
    public ResponseEntity<LetraResource> getLetraById(@PathVariable Long id) {
        return letraQueryService.handle(new GetLetraByIdQuery(id))
                .map(letra -> {
                    LetraResource letraResource = LetraResourceFromEntityAssembler.toResourceFromEntity(letra);
                    return ResponseEntity.ok(letraResource);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
