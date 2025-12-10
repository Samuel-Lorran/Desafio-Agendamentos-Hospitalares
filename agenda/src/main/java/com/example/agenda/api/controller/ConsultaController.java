package com.example.agenda.api.controller;

import com.example.agenda.api.dto.CancelamentoRequest;
import com.example.agenda.api.dto.ConsultaRequest;
import com.example.agenda.domain.entity.Consulta;
import com.example.agenda.domain.service.ConsultaService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    public ConsultaController(ConsultaService service) {
        this.service = service;
    }

    @PostMapping
    public Consulta agendar(@RequestBody @Valid ConsultaRequest req) {
        return service.agendar(
                req.medicoId(),
                req.pacienteId(),
                req.dataHora(),
                req.descricao()
        );
    }

    @GetMapping
    public List<Consulta> listar() {
        return service.listar();
    }

    @PostMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Long id,
                         @RequestBody @Valid CancelamentoRequest req) {
        service.cancelar(id, req.motivo());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
