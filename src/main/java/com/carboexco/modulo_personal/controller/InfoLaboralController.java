package com.carboexco.modulo_personal.controller;


import com.carboexco.modulo_personal.entity.InfoLaboral;
import com.carboexco.modulo_personal.repository.InfoLaboralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/info-laboral")
public class InfoLaboralController {

    @Autowired
    private InfoLaboralRepository infoLaboralRepository;

    // Obtener toda la información laboral
    @GetMapping
    public List<InfoLaboral> getAllInfoLaboral() {
        return infoLaboralRepository.findAll();
    }

    // Obtener la información laboral por su ID
    @GetMapping("/{idPersona}/{idCargo}")
    public InfoLaboral getInfoLaboralById(@PathVariable String idPersona, @PathVariable Integer idCargo) {
        Optional<InfoLaboral> infoLaboral = infoLaboralRepository.findFirstById_IdPersona_IdPersonaAndId_IdCargoOrderById_IdPersona_IdPersonaAsc(idPersona,idCargo);

        if (infoLaboral.isPresent()) {
            return infoLaboral.get();
        }

        return null;
    }

    // Crear nueva información laboral
    @PostMapping
    public InfoLaboral createInfoLaboral(@RequestBody InfoLaboral infoLaboral) {
        return infoLaboralRepository.save(infoLaboral);
    }

    // Actualizar información laboral por su ID
    @PutMapping("/{idPersona}/{idCargo}")
    public InfoLaboral updateInfoLaboral(@PathVariable String idPersona, @PathVariable Integer idCargo, @RequestBody InfoLaboral infoLaboral) {
        Optional<InfoLaboral> currentInfoLaboral = infoLaboralRepository.findFirstById_IdPersona_IdPersonaAndId_IdCargoOrderById_IdPersona_IdPersonaAsc(idPersona,idCargo);

        if (currentInfoLaboral.isPresent()) {
            InfoLaboral updatedInfoLaboral = currentInfoLaboral.get();
            updatedInfoLaboral.setIdHojaVida(infoLaboral.getIdHojaVida());
            updatedInfoLaboral.setIdActaRetiro(infoLaboral.getIdActaRetiro());
            updatedInfoLaboral.setEstadoLaboral(infoLaboral.getEstadoLaboral());
            updatedInfoLaboral.setAfiliacionSeguridadSocial(infoLaboral.getAfiliacionSeguridadSocial());
            updatedInfoLaboral.setEps(infoLaboral.getEps());
            updatedInfoLaboral.setArl(infoLaboral.getArl());
            updatedInfoLaboral.setCajaCompensacionFamiliar(infoLaboral.getCajaCompensacionFamiliar());
            updatedInfoLaboral.setPensiones(infoLaboral.getPensiones());
            updatedInfoLaboral.setMotivoRetiro(infoLaboral.getMotivoRetiro());
            updatedInfoLaboral.setFechaInicio(infoLaboral.getFechaInicio());
            updatedInfoLaboral.setFechaRetiro(infoLaboral.getFechaRetiro());
            return infoLaboralRepository.save(updatedInfoLaboral);
        }

        return null;
    }

    // Eliminar información laboral por su ID
    @DeleteMapping("/{idPersona}/{idCargo}")
    public InfoLaboral deleteInfoLaboral(@PathVariable String idPersona, @PathVariable Integer idCargo) {
        Optional<InfoLaboral> infoLaboral = infoLaboralRepository.findFirstById_IdPersona_IdPersonaAndId_IdCargoOrderById_IdPersona_IdPersonaAsc(idPersona, idCargo);

        if (infoLaboral.isPresent()) {
            InfoLaboral deletedInfoLaboral = infoLaboral.get();
            infoLaboralRepository.deleteById(deletedInfoLaboral.getId());
            return deletedInfoLaboral;
        }
        return null;
    }
}

