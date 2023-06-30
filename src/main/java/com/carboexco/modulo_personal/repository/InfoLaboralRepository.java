package com.carboexco.modulo_personal.repository;

import com.carboexco.modulo_personal.entity.InfoLaboral;
import com.carboexco.modulo_personal.entity.InfoLaboralId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InfoLaboralRepository extends JpaRepository<InfoLaboral, InfoLaboralId> {
    Optional<InfoLaboral> findFirstById_IdPersonaAndId_IdCargoOrderById_IdPersonaAsc(String idPersona, Integer idCargo);

    @Override
    void deleteById(InfoLaboralId infoLaboralId);
}