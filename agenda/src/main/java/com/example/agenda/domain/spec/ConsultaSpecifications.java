package com.example.agenda.domain.spec;

import org.springframework.data.jpa.domain.Specification;

import com.example.agenda.domain.entity.Consulta;

public class ConsultaSpecifications {

    public static Specification<Consulta> naoDeletada() {
        return (root, query, cb) -> cb.isFalse(root.get("deleted"));
    }

    public static Specification<Consulta> porMedico(Long medicoId) {
        return (root, query, cb) -> cb.equal(root.get("medico").get("id"), medicoId);
    }

    public static Specification<Consulta> porPaciente(Long pacienteId) {
        return (root, query, cb) -> cb.equal(root.get("paciente").get("id"), pacienteId);
    }
}
