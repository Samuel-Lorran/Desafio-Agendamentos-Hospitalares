package com.example.agenda.api.controller;

import com.example.agenda.api.dto.MedicoRequest;
import com.example.agenda.api.dto.MedicoResponse;
import com.example.agenda.api.dto.MedicoUpdateRequest;
import com.example.agenda.domain.entity.Medico;
import com.example.agenda.domain.service.MedicoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicoResponse> criar(@RequestBody @Valid MedicoRequest request) {
        Medico medico = new Medico();
                medico.setNome(request.nome());
                medico.setCrm(request.crm());
                medico.setEspecialidade(request.especialidade());
        
        Medico criado = service.criar(medico);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MedicoResponse.fromEntity(criado));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> listar() {
        List<Medico> medicos = service.listar();
        List<MedicoResponse> response = medicos.stream()
                .map(MedicoResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscar(@PathVariable Long id) {
        Medico medico = service.buscarPorId(id);
        return ResponseEntity.ok(MedicoResponse.fromEntity(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> atualizar(
            @PathVariable Long id, 
            @RequestBody @Valid MedicoUpdateRequest request) {
        
        Medico medicoAtualizado = service.atualizar(id, request);
        return ResponseEntity.ok(MedicoResponse.fromEntity(medicoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
