package com.example.inviertelow.platform.letra.interfaces.rest;

import com.example.inviertelow.platform.letra.application.commandservices.CalculoCarteraLetraCommandServiceImpl;
import com.example.inviertelow.platform.letra.application.queryservices.CalculoCarteraQueryServiceImpl;
import com.example.inviertelow.platform.letra.domain.model.commands.calculoCarteraLetra.UpdateCalculoCarteraLetraCommand;
import com.example.inviertelow.platform.letra.domain.model.queries.calculoCarteraLetra.GetCalculoByCarteraLetraIdQuery;
import com.example.inviertelow.platform.letra.interfaces.rest.resources.CalculoCarteraLetraResource;
import com.example.inviertelow.platform.letra.interfaces.rest.transform.CalculoCarteraLetraFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/calculo-cartera-letras", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Calculo Cartera Letras", description = "Gestión de cálculos financieros para carteras de letras")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CalculoCarteraLetraController {

    private final CalculoCarteraQueryServiceImpl queryService;

    public CalculoCarteraLetraController(CalculoCarteraQueryServiceImpl queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/{carteraLetraId}")
    @Operation(summary = "Obtiene el cálculo de una Cartera de Letras por ID de Cartera", description = "Recupera el cálculo financiero consolidado de una cartera de letras específica usando su ID.")
    public CalculoCarteraLetraResource getCalculoByCarteraLetraId(@PathVariable Long carteraLetraId) {
        var query = new GetCalculoByCarteraLetraIdQuery(carteraLetraId);
        var calculoCarteraLetra = queryService.handle(query).orElseThrow(() -> new RuntimeException("Calculo de Cartera no encontrado"));
        return CalculoCarteraLetraFromEntityAssembler.toResourceFromEntity(calculoCarteraLetra);
    }
}
