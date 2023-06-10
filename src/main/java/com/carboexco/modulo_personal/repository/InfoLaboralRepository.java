package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.InfoLaboral;
import com.carboexco.modulo_personal.entity.InfoLaboralId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoLaboralRepository extends JpaRepository<InfoLaboral, InfoLaboralId> {
    InfoLaboral findFirstById_IdCargoOrderById_IdPersonaAsc(Integer idCargo);
}