package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}