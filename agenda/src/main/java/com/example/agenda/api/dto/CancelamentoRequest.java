package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotNull;

public record CancelamentoRequest(
        @NotNull String motivo
) {}
