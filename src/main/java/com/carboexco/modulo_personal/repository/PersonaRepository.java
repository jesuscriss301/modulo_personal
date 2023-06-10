package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, String> {

}