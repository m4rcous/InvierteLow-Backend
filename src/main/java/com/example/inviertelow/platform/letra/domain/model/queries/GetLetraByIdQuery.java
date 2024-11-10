package com.example.inviertelow.platform.letra.domain.model.queries;

public record GetLetraByIdQuery(Long letraId) {
    public GetLetraByIdQuery {
        if (letraId == null || letraId <= 0) {
            throw new IllegalArgumentException("El ID de la letra no puede ser nulo o menor o igual a 0");
        }
    }
}