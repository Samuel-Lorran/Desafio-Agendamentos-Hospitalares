package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record MedicoRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        
        @NotBlank(message = "CRM é obrigatório")
        @Pattern(regexp = "\\d{6,7}-[A-Z]{2}", message = "CRM deve seguir formato XXXXXX-UF ou XXXXXXX-UF")
        String crm,
        
        @NotBlank(message = "Especialidade é obrigatória")
        String especialidade
) {}
