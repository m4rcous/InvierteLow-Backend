package com.example.inviertelow.platform.letra.interfaces.rest.resources;

import java.util.List;

public record CarteraLetraResource(
        Long id,
        List<Long> letrasIds,
        Long calculoCarteraLetraId
) {}
