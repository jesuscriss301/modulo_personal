package com.carboexco.modulo_personal.controller;

import com.carboexco.modulo_personal.entity.Departamento;
import com.carboexco.modulo_personal.repository.DepartamentoRepository;
import com.carboexco.modulo_personal.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartamentoController {

    private final DepartamentoRepository departamentoRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @Autowired
    public DepartamentoController(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> getAllDepartamentos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Departamento> departamentos = departamentoRepository.findAll();
            return ResponseEntity.ok(departamentos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Departamento> departamento = departamentoRepository.findById(id);
            if (departamento.isPresent()) {
                return ResponseEntity.ok(departamento.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<Departamento> createDepartamento(@RequestHeader("Authorization") String bearerToken, @RequestBody Departamento departamento) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Departamento createdDepartamento = departamentoRepository.save(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartamento);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Departamento departamento) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Departamento> currentDepartamento = departamentoRepository.findById(id);
            if (currentDepartamento.isPresent()) {
                Departamento updatedDepartamento = currentDepartamento.get();
                updatedDepartamento.setNombre(departamento.getNombre());
                Departamento savedDepartamento = departamentoRepository.save(updatedDepartamento);
                return ResponseEntity.ok(savedDepartamento);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> deleteDepartamento(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Departamento> departamento = departamentoRepository.findById(id);
            if (departamento.isPresent()) {
                departamentoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}