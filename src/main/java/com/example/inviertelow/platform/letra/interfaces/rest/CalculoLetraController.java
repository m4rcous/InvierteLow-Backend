package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.domain.model.queries.calculoLetra.GetCalculoByLetraIdQuery;
import com.example.inviertelow.platform.letra.domain.services.calculoLetra.CalculoLetraQueryService;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CalculoLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CalculoLetraFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculo-letra")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Tag(name = "Calculo Letras", description = "Operaciones para obtener el cálculo financiero de una letra")
public class CalculoLetraController {

    private final CalculoLetraQueryService calculoLetraQueryService;

    public CalculoLetraController(CalculoLetraQueryService calculoLetraQueryService) {
        this.calculoLetraQueryService = calculoLetraQueryService;
    }

    @GetMapping("/letra/{letraId}")
    @Operation(summary = "Obtener el cálculo de una letra por ID", description = "Recupera los cálculos financieros asociados a una letra utilizando su ID.")
    public ResponseEntity<CalculoLetraResource> getCalculoByLetraId(@PathVariable Long letraId) {
        var query = new GetCalculoByLetraIdQuery(letraId);
        var calculoLetra = calculoLetraQueryService.handle(query);

        return calculoLetra
                .map(CalculoLetraFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
