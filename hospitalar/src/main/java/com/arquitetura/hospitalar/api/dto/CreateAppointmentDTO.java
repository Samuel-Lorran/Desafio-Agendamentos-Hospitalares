package com.arquitetura.hospitalar.api.dto;

import java.time.Instant;

public record CreateAppointmentDTO(
        Long patientId,
        Long doctorId,
        Instant startAt,
        Instant endAt,
        String description
) {}
