package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByIdCargo_Id(Integer id);

    Persona findFirstByCedula(Long cedula);

    @Query("select p from Persona p where p.nombres like ?1")
    List<Persona> findByNombresLike(String nombres);

    @Query("select p from Persona p where p.apellidos like ?1")
    List<Persona> findByApellidosLike(String apellidos);

}