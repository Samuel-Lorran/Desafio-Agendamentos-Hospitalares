package com.arquitetura.hospitalar.domain.repository;

import com.arquitetura.hospitalar.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>,
        JpaSpecificationExecutor<Doctor> {}
