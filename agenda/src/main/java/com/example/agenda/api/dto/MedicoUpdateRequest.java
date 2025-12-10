package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MedicoUpdateRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        
        @Pattern(regexp = "\\d{6,7}-[A-Z]{2}", message = "CRM deve seguir formato XXXXXX-UF ou XXXXXXX-UF")
        String crm,
        
        @NotBlank(message = "Especialidade é obrigatória")
        String especialidade
) {}
