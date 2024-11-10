package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.domain.model.queries.GetCalculoByLetraIdQuery;
import com.example.inviertelow.platform.letra.domain.services.CalculoLetraQueryService;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CalculoLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CalculoLetraFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculos")
@Tag(name = "Calculos", description = "bla bla bla bla")
public class CalculoLetraController {

    private final CalculoLetraQueryService calculoLetraQueryService;

    public CalculoLetraController(CalculoLetraQueryService calculoLetraQueryService) {
        this.calculoLetraQueryService = calculoLetraQueryService;
    }

    @GetMapping("/letra/{letraId}")
    public ResponseEntity<CalculoLetraResource> getCalculoByLetraId(@PathVariable Long letraId) {
        var query = new GetCalculoByLetraIdQuery(letraId);
        var calculoLetra = calculoLetraQueryService.handle(query);

        return calculoLetra
                .map(CalculoLetraFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
