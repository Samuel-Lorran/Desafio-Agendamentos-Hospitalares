package com.example.agenda.api.dto;

import com.example.agenda.domain.entity.Medico;

public record MedicoResponse(
        Long id,
        String nome,
        String crm,
        String especialidade
) {
    public static MedicoResponse fromEntity(Medico medico) {
        return new MedicoResponse(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
