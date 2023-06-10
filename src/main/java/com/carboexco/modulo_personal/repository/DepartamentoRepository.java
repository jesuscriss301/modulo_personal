package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}