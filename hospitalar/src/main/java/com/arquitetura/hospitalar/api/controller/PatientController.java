package com.arquitetura.hospitalar.api.controller;

import com.arquitetura.hospitalar.domain.model.Patient;
import com.arquitetura.hospitalar.domain.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientRepository repo;

    public PatientController(PatientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Patient> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Patient find(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Patient create(@RequestBody Patient p) {
        return repo.save(p);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient p) {
        Patient db = repo.findById(id).orElseThrow();
        db.setFullName(p.getFullName());
        db.setEmail(p.getEmail());
        db.setPhone(p.getPhone());
        return repo.save(db);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);  // soft delete
    }
}
