package com.example.agenda.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.agenda.api.dto.PacienteUpdateRequest;
import com.example.agenda.domain.entity.Paciente;
import com.example.agenda.domain.repository.PacienteRepository;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    public Paciente criar(Paciente p) {
        p.setDeleted(false);
        return repo.save(p);
    }

    public List<Paciente> listar() {
        return repo.findByDeletedFalse();
    }

    @Transactional
    public Paciente atualizar(Long id, PacienteUpdateRequest request) {
        Paciente existente = repo.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        
        existente.setNome(request.nome());
        if (request.cpf() != null) {
            existente.setCpf(request.cpf());
        }
        if (request.telefone() != null) {
            existente.setTelefone(request.telefone());
        }
        
        return repo.save(existente);
    }

    public void deletar(Long id) {
        Paciente p = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));
        p.setDeleted(true);
        repo.save(p);
    }
    
    @Transactional(readOnly = true)
    public Paciente buscarPorId(Long id) {
        return repo.findById(id)
                .filter(paciente -> !paciente.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado ou foi deletado"));
    }
}
