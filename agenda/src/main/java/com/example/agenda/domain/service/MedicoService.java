package com.example.agenda.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.agenda.api.dto.MedicoUpdateRequest;
import com.example.agenda.domain.entity.Medico;
import com.example.agenda.domain.repository.MedicoRepository;

@Service
public class MedicoService {

    private final MedicoRepository repo;

    public MedicoService(MedicoRepository repo) {
        this.repo = repo;
    }

    public Medico criar(Medico m) {
        m.setDeleted(false);
        return repo.save(m);
    }

    public List<Medico> listar() {
        return repo.findByDeletedFalse();
    }

    public Medico buscarPorId(Long id) {
        return repo.findById(id)
                .filter(medico -> !medico.isDeleted())
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
    }

    @Transactional
    public Medico atualizar(Long id, MedicoUpdateRequest request) {
        Medico existente = buscarPorId(id);
        
        existente.setNome(request.nome());
        if (request.crm() != null) {
            existente.setCrm(request.crm());
        }
        if (request.especialidade() != null) {
            existente.setEspecialidade(request.especialidade());
        }
        
        return repo.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        Medico m = buscarPorId(id);
        m.setDeleted(true);
        repo.save(m);
    }
}
