package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.domain.model.aggregates.Letra;
import com.example.inviertelow.platform.letra.domain.model.commands.letra.CreateLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.queries.letra.GetAllLetrasQuery;
import com.example.inviertelow.platform.letra.domain.model.queries.letra.GetLetraByIdQuery;
import com.example.inviertelow.platform.letra.domain.services.letra.LetraCommandService;
import com.example.inviertelow.platform.letra.domain.services.letra.LetraQueryService;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CreateLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.LetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CreateLetraCommandFromResourceAssembler;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.LetraResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/v1/letras", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Letras", description = "Operaciones para la creación y consulta de letras financieras")
public class LetraController {

    private final LetraCommandService letraCommandService;
    private final LetraQueryService letraQueryService;

    public LetraController(LetraCommandService letraCommandService, LetraQueryService letraQueryService) {
        this.letraCommandService = letraCommandService;
        this.letraQueryService = letraQueryService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear una nueva letra", description = "Crea una nueva letra financiera con la información proporcionada.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<LetraResource> createLetra(@RequestBody CreateLetraResource createLetraResource) {
        CreateLetraCommand command = CreateLetraCommandFromResourceAssembler.toCommandFromResource(createLetraResource);
        Letra nuevaLetra = letraCommandService.handle(command);
        LetraResource letraResource = LetraResourceFromEntityAssembler.toResourceFromEntity(nuevaLetra);

        return ResponseEntity.created(URI.create("/api/v1/letras/" + nuevaLetra.getId())).body(letraResource);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las letras", description = "Recupera todas las letras financieras disponibles en el sistema.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<LetraResource>> getAllLetras() {
        List<Letra> letras = letraQueryService.handle(new GetAllLetrasQuery());
        List<LetraResource> letraResources = letras.stream()
                .map(LetraResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(letraResources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una letra por ID", description = "Obtiene la información detallada de una letra específica utilizando su ID.", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<LetraResource> getLetraById(@PathVariable Long id) {
        return letraQueryService.handle(new GetLetraByIdQuery(id))
                .map(letra -> {
                    LetraResource letraResource = LetraResourceFromEntityAssembler.toResourceFromEntity(letra);
                    return ResponseEntity.ok(letraResource);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
