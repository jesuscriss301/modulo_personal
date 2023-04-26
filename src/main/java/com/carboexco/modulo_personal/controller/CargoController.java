package com.carboexco.modulo_personal.controller;

import com.carboexco.modulo_personal.entity.Cargo;
import com.carboexco.modulo_personal.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    CargoRepository cargoRepository;

    @GetMapping
    public List<Cargo> getCargoAll() {
        return cargoRepository.findAll();
    }


    @GetMapping("/{id}")
    public Cargo getCargobyId(@PathVariable int id) {

        Optional<Cargo> cargo = cargoRepository.findById(id);

        if (cargo.isPresent()) {
            return cargo.get();
        }
        return null;
    }

    @PostMapping
    public Cargo postCargo(@RequestBody Cargo cargo) {
        cargoRepository.save(cargo);
        return cargo;
    }

    @PutMapping("/{id}")
    public Cargo putCargobyId(@PathVariable int id, @RequestBody Cargo cargo) {

        Optional<Cargo> cargoCurrent = cargoRepository.findById(id);

        if (cargoCurrent.isPresent()) {
            Cargo cargoReturn = cargoCurrent.get();

            cargoReturn.setNombre(cargo.getNombre());

            cargoRepository.save(cargoReturn);
            return cargoReturn;
        }

        return null;
    }


    @DeleteMapping("/{id}")
    public Cargo deleteCargobyId(@PathVariable int id) {

        Optional<Cargo> cargo = cargoRepository.findById(id);

        if (cargo.isPresent()) {
            Cargo cargoReturn = cargo.get();
            cargoRepository.deleteById(id);
            return cargoReturn;
        }

        return null;
    }

}
