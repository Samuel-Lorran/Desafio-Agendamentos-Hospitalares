package com.example.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agenda.domain.entity.Paciente;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByDeletedFalse();
}
