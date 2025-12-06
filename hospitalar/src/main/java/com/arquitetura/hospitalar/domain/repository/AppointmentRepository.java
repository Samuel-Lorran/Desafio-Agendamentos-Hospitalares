package com.arquitetura.hospitalar.domain.repository;

import com.arquitetura.hospitalar.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>,
        JpaSpecificationExecutor<Appointment> {

    @Query("select a from Appointment a where a.doctor.id = :doctorId " +
            "and a.status <> 'CANCELLED' and (a.startAt < :end and a.endAt > :start)")
    List<Appointment> findOverlapsDoctor(
            @Param("doctorId") Long doctorId,
            @Param("start") Instant start,
            @Param("end") Instant end);

    @Query("select a from Appointment a where a.patient.id = :patientId " +
            "and a.status <> 'CANCELLED' and (a.startAt < :end and a.endAt > :start)")
    List<Appointment> findOverlapsPatient(
            @Param("patientId") Long patientId,
            @Param("start") Instant start,
            @Param("end") Instant end);
}
