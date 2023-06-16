package com.carboexco.modulo_personal.controller;


import com.carboexco.modulo_personal.entity.Cargo;
import com.carboexco.modulo_personal.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    // Obtener todos los cargos
    @GetMapping
    public List<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    // Obtener un cargo por su ID
    @GetMapping("/{id}")
    public Cargo getCargoById(@PathVariable int id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);

        if (cargo.isPresent()) {
            return cargo.get();
        }

        return null;
    }

    // Crear un nuevo cargo
    @PostMapping
    public Cargo createCargo(@RequestBody Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    // Actualizar un cargo existente por su ID
    @PutMapping("/{id}")
    public Cargo updateCargo(@PathVariable int id, @RequestBody Cargo cargo) {
        Optional<Cargo> currentCargo = cargoRepository.findById(id);

        if (currentCargo.isPresent()) {
            Cargo updatedCargo = currentCargo.get();
            updatedCargo.setNombre(cargo.getNombre());
            updatedCargo.setTipoCargo(cargo.getTipoCargo());
            updatedCargo.setDepartamento(cargo.getDepartamento());
            updatedCargo.setArea(cargo.getArea());
            return cargoRepository.save(updatedCargo);
        }

        return null;
    }

    // Eliminar un cargo por su ID
    @DeleteMapping("/{id}")
    public Cargo deleteCargo(@PathVariable int id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);

        if (cargo.isPresent()) {
            Cargo deletedCargo = cargo.get();
            cargoRepository.deleteById(id);
            return deletedCargo;
        }

        return null;
    }
}
