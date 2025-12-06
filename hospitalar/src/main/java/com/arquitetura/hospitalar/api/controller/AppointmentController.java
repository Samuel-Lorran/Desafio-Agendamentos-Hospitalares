package com.arquitetura.hospitalar.api.controller;

import com.arquitetura.hospitalar.api.dto.*;
import com.arquitetura.hospitalar.domain.model.Appointment;
import com.arquitetura.hospitalar.domain.service.SchedulingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final SchedulingService scheduling;

    public AppointmentController(SchedulingService scheduling) {
        this.scheduling = scheduling;
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody CreateAppointmentDTO dto) {
        return ResponseEntity.ok(
                scheduling.schedule(
                        dto.patientId(),
                        dto.doctorId(),
                        dto.startAt(),
                        dto.endAt(),
                        dto.description()
                )
        );
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancel(@PathVariable Long id, @RequestBody CancelAppointmentDTO dto) {
        return ResponseEntity.ok(scheduling.cancel(id, dto.reason()));
    }
}
