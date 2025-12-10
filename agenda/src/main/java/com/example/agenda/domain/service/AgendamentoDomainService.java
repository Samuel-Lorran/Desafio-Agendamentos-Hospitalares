package com.example.agenda.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.agenda.domain.entity.Consulta;
import com.example.agenda.domain.repository.ConsultaRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoDomainService {

    private final ConsultaRepository consultaRepository;
    private static final Duration DURAÇÃO_PADRAO = Duration.ofMinutes(30);

    public AgendamentoDomainService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Transactional(readOnly = true)
    public void validarAgendamento(Consulta nova) {
        LocalDateTime inicio = nova.getDataHora();
        LocalDateTime fim = inicio.plus(DURAÇÃO_PADRAO);

        List<Consulta> medicoConflitos =
                consultaRepository.findByMedicoIdAndDeletedFalseAndDataHoraBetween(
                        nova.getMedico().getId(), inicio, fim);

        if (!medicoConflitos.isEmpty()) {
            throw new IllegalArgumentException("Médico já possui consulta neste horário.");
        }

        List<Consulta> pacienteConflitos =
                consultaRepository.findByPacienteIdAndDeletedFalseAndDataHoraBetween(
                        nova.getPaciente().getId(), inicio, fim);

        if (!pacienteConflitos.isEmpty()) {
            throw new IllegalArgumentException("Paciente já possui consulta neste horário.");
        }
    }

    @Transactional
    public void cancelar(Consulta consulta, String motivo) {
        if (consulta.getStatus() == Consulta.StatusConsulta.CANCELADA) {
            return;
        }
        consulta.setStatus(Consulta.StatusConsulta.CANCELADA);
        consulta.setMotivoCancelamento(motivo);
    }
}
