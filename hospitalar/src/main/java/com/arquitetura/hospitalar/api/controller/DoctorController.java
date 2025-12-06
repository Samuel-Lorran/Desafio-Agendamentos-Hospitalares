package com.arquitetura.hospitalar.api.controller;

import com.arquitetura.hospitalar.domain.model.Doctor;
import com.arquitetura.hospitalar.domain.repository.DoctorRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

	private final DoctorRepository repo;

    public DoctorController(DoctorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Doctor> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Doctor find(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor d) {
        return repo.save(d);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor d) {
    	Doctor db = repo.findById(id).orElseThrow();
        db.setFullName(d.getFullName());
        db.setSpecialty(d.getSpecialty());
        return repo.save(db);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);  // soft delete
    }
}
