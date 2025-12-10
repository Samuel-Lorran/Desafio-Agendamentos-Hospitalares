package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PacienteUpdateRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        
        @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
        String cpf,
        
        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) {}