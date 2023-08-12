package com.carboexco.modulo_personal.controller;

import com.carboexco.modulo_personal.entity.Cargo;
import com.carboexco.modulo_personal.repository.CargoRepository;
import com.carboexco.modulo_personal.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cargos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CargoController {

    private final CargoRepository cargoRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @Autowired
    public CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cargo>> getAllCargos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Cargo> cargos = cargoRepository.findAll();
            return ResponseEntity.ok(cargos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/departamento/{id}")
    public ResponseEntity<List<Cargo>> getAllCargosByDepartamento(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Cargo> cargos = cargoRepository.findByDepartamento_IdOrderByNombreAsc(id);
            return ResponseEntity.ok(cargos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCargoById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargo> cargo = cargoRepository.findById(id);
            if (cargo.isPresent()) {
                return ResponseEntity.ok(cargo.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<Cargo> createCargo(@RequestHeader("Authorization") String bearerToken, @RequestBody Cargo cargo) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Cargo createdCargo = cargoRepository.save(cargo);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCargo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> updateCargo(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Cargo cargo) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargo> currentCargo = cargoRepository.findById(id);
            if (currentCargo.isPresent()) {
                Cargo updatedCargo = currentCargo.get();
                updatedCargo.setNombre(cargo.getNombre());
                updatedCargo.setTipoCargo(cargo.getTipoCargo());
                updatedCargo.setDepartamento(cargo.getDepartamento());
                updatedCargo.setArea(cargo.getArea());
                Cargo savedCargo = cargoRepository.save(updatedCargo);
                return ResponseEntity.ok(savedCargo);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cargo> deleteCargo(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargo> cargo = cargoRepository.findById(id);
            if (cargo.isPresent()) {
                cargoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}