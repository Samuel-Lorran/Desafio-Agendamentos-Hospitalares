package com.arquitetura.hospitalar.domain.service;

import com.arquitetura.hospitalar.domain.model.*;
import com.arquitetura.hospitalar.domain.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SchedulingService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public SchedulingService(AppointmentRepository appointmentRepo,
                             DoctorRepository doctorRepo,
                             PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    @Transactional
    public Appointment schedule(Long patientId, Long doctorId, Instant start, Instant end, String desc) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        var doctorOverlap = appointmentRepo.findOverlapsDoctor(doctorId, start, end);
        if (!doctorOverlap.isEmpty()) throw new IllegalStateException("Médico já possui consulta no horário");

        var patientOverlap = appointmentRepo.findOverlapsPatient(patientId, start, end);
        if (!patientOverlap.isEmpty()) throw new IllegalStateException("Paciente já possui consulta no horário");

        Appointment a = new Appointment();
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setStartAt(start);
        a.setEndAt(end);
        a.setDescription(desc);
        a.setStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepo.save(a);
    }

    @Transactional
    public Appointment cancel(Long id, String reason) {
        Appointment a = appointmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado"));

        a.setStatus(AppointmentStatus.CANCELLED);
        a.setCancelReason(reason);
        a.setCanceledAt(Instant.now());

        return appointmentRepo.save(a);
    }
}
