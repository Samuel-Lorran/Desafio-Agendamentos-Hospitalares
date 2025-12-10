package com.example.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.agenda.domain.entity.Consulta;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>,
        JpaSpecificationExecutor<Consulta> {

    List<Consulta> findByMedicoIdAndDeletedFalseAndDataHoraBetween(
            Long medicoId, LocalDateTime inicio, LocalDateTime fim);

    List<Consulta> findByPacienteIdAndDeletedFalseAndDataHoraBetween(
            Long pacienteId, LocalDateTime inicio, LocalDateTime fim);
}
