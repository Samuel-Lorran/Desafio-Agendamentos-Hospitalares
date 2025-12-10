package com.example.agenda.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agenda.domain.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    List<Medico> findByDeletedFalse();
}
