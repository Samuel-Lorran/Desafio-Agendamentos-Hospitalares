package com.example.agenda.api.dto;

import com.example.agenda.domain.entity.Paciente;

public record PacienteResponse(
        Long id,
        String nome,
        String cpf,
        String telefone
) {
    public static PacienteResponse fromEntity(Paciente paciente) {
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone()
        );
    }
}
