package com.arquitetura.hospitalar.domain.specification;

import com.arquitetura.hospitalar.domain.model.Appointment;
import com.arquitetura.hospitalar.domain.model.AppointmentStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class AppointmentSpecifications {

    public static Specification<Appointment> doctor(Long doctorId) {
        return (root, q, cb) ->
                doctorId == null ? cb.conjunction() : cb.equal(root.get("doctor").get("id"), doctorId);
    }

    public static Specification<Appointment> patient(Long patientId) {
        return (root, q, cb) ->
                patientId == null ? cb.conjunction() : cb.equal(root.get("patient").get("id"), patientId);
    }

    public static Specification<Appointment> status(AppointmentStatus status) {
        return (root, q, cb) ->
                status == null ? cb.conjunction() : cb.equal(root.get("status"), status);
    }

    public static Specification<Appointment> overlaps(Instant from, Instant to) {
        return (root, q, cb) -> {
            if (from == null || to == null) return cb.conjunction();
            return cb.and(cb.lessThan(root.get("startAt"), to),
                          cb.greaterThan(root.get("endAt"), from));
        };
    }
}
