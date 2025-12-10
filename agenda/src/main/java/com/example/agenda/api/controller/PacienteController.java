package com.example.agenda.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.agenda.domain.service.PacienteService;
import com.example.agenda.api.dto.PacienteRequest;
import com.example.agenda.api.dto.PacienteResponse;
import com.example.agenda.api.dto.PacienteUpdateRequest;
import com.example.agenda.domain.entity.Paciente;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> criar(@RequestBody @Valid PacienteRequest request) {
        Paciente paciente = new Paciente();
        	paciente.setNome(request.nome());
        	paciente.setCpf(request.cpf());
        	paciente.setTelefone(request.telefone());
        
        Paciente criado = service.criar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PacienteResponse.fromEntity(criado));
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listar() {
        List<Paciente> pacientes = service.listar();
        List<PacienteResponse> response = pacientes.stream()
                .map(PacienteResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscar(@PathVariable Long id) {
        Paciente paciente = service.buscarPorId(id);
        return ResponseEntity.ok(PacienteResponse.fromEntity(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> atualizar(
            @PathVariable Long id, 
            @RequestBody @Valid PacienteUpdateRequest request) {
        
        Paciente pacienteAtualizado = service.atualizar(id, request);
        return ResponseEntity.ok(PacienteResponse.fromEntity(pacienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
