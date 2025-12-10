package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaRequest(
        @NotNull Long medicoId,
        @NotNull Long pacienteId,
        @NotNull LocalDateTime dataHora,
        String descricao
) {}