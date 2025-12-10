package com.example.agenda.domain.service;

import com.example.agenda.domain.entity.Consulta;
import com.example.agenda.domain.entity.Medico;
import com.example.agenda.domain.entity.Paciente;
import com.example.agenda.domain.repository.ConsultaRepository;
import com.example.agenda.domain.repository.MedicoRepository;
import com.example.agenda.domain.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final MedicoRepository medicoRepo;
    private final PacienteRepository pacienteRepo;
    private final AgendamentoDomainService domainService;

    public ConsultaService(ConsultaRepository consultaRepo,
                           MedicoRepository medicoRepo,
                           PacienteRepository pacienteRepo,
                           AgendamentoDomainService domainService) {
        this.consultaRepo = consultaRepo;
        this.medicoRepo = medicoRepo;
        this.pacienteRepo = pacienteRepo;
        this.domainService = domainService;
    }

    @Transactional
    public Consulta agendar(Long medicoId, Long pacienteId,
                            LocalDateTime dataHora, String descricao) {
        Medico medico = medicoRepo.findById(medicoId)
                .orElseThrow(() -> new IllegalArgumentException("Médico não encontrado"));
        Paciente paciente = pacienteRepo.findById(pacienteId)
                .orElseThrow(() -> new IllegalArgumentException("Paciente não encontrado"));

        Consulta consulta = new Consulta();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setDataHora(dataHora);
        consulta.setDescricao(descricao);
        consulta.setStatus(Consulta.StatusConsulta.AGENDADA);
        consulta.setDeleted(false);

        domainService.validarAgendamento(consulta);
        return consultaRepo.save(consulta);
    }

    @Transactional
    public void cancelar(Long id, String motivo) {
        Consulta consulta = consultaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        domainService.cancelar(consulta, motivo);
        consultaRepo.save(consulta);
    }

    @Transactional(readOnly = true)
    public List<Consulta> listar() {
        return consultaRepo.findAll()
                .stream().filter(c -> !c.isDeleted()).toList();
    }

    @Transactional
    public void deletar(Long id) {
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta não encontrada"));
        c.setDeleted(true);
        consultaRepo.save(c);
    }
}
