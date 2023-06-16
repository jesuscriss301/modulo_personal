package com.carboexco.modulo_personal.controller;


import com.carboexco.modulo_personal.entity.Departamento;
import com.carboexco.modulo_personal.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Departamento getDepartamentoById(@PathVariable int id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);

        if (departamento.isPresent()) {
            return departamento.get();
        }

        return null;
    }

    @PostMapping
    public Departamento createDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @PutMapping("/{id}")
    public Departamento updateDepartamento(@PathVariable int id, @RequestBody Departamento departamento) {
        Optional<Departamento> currentDepartamento = departamentoRepository.findById(id);

        if (currentDepartamento.isPresent()) {
            Departamento updatedDepartamento = currentDepartamento.get();
            updatedDepartamento.setNombre(departamento.getNombre());
            return departamentoRepository.save(updatedDepartamento);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Departamento deleteDepartamento(@PathVariable int id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);

        if (departamento.isPresent()) {
            Departamento deletedDepartamento = departamento.get();
            departamentoRepository.deleteById(id);
            return deletedDepartamento;
        }

        return null;
    }
}

