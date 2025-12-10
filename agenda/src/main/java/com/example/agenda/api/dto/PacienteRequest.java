package com.example.agenda.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PacienteRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        
        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
        String cpf,
        
        @NotBlank(message = "Telefone é obrigatório")
        String telefone
) {}
