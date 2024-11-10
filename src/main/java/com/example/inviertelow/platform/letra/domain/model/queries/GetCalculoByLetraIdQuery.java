package com.example.inviertelow.platform.letra.domain.model.queries;

public record GetCalculoByLetraIdQuery(Long letraId) {
    public GetCalculoByLetraIdQuery {
        if (letraId == null || letraId <= 0) {
            throw new IllegalArgumentException("El ID de la letra debe ser un valor positivo y no nulo");
        }
    }
}
