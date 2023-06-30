package com.carboexco.modulo_personal.controller;

import com.carboexco.modulo_personal.entity.InfoLaboral;
import com.carboexco.modulo_personal.repository.InfoLaboralRepository;
import com.carboexco.modulo_personal.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/info-laboral")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InfoLaboralController {

    private final InfoLaboralRepository infoLaboralRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @Autowired
    public InfoLaboralController(InfoLaboralRepository infoLaboralRepository) {
        this.infoLaboralRepository = infoLaboralRepository;
    }

    @GetMapping
    public ResponseEntity<List<InfoLaboral>> getAllInfoLaboral(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<InfoLaboral> infoLaboralList = infoLaboralRepository.findAll();
            return ResponseEntity.ok(infoLaboralList);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{idPersona}/{idCargo}")
    public ResponseEntity<InfoLaboral> getInfoLaboralById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idPersona, @PathVariable Integer idCargo) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<InfoLaboral> infoLaboral = infoLaboralRepository.findFirstById_IdPersonaAndId_IdCargoOrderById_IdPersonaAsc(idPersona, idCargo);
            if (infoLaboral.isPresent()) {
                return ResponseEntity.ok(infoLaboral.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<InfoLaboral> createInfoLaboral(@RequestHeader("Authorization") String bearerToken, @RequestBody InfoLaboral infoLaboral) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            InfoLaboral createdInfoLaboral = infoLaboralRepository.save(infoLaboral);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdInfoLaboral);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{idPersona}/{idCargo}")
    public ResponseEntity<InfoLaboral> updateInfoLaboral(@RequestHeader("Authorization") String bearerToken, @PathVariable String idPersona, @PathVariable Integer idCargo, @RequestBody InfoLaboral infoLaboral) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<InfoLaboral> currentInfoLaboral = infoLaboralRepository.findFirstById_IdPersonaAndId_IdCargoOrderById_IdPersonaAsc(idPersona, idCargo);
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
                InfoLaboral savedInfoLaboral = infoLaboralRepository.save(updatedInfoLaboral);
                return ResponseEntity.ok(savedInfoLaboral);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{idPersona}/{idCargo}")
    public ResponseEntity<InfoLaboral> deleteInfoLaboral(@RequestHeader("Authorization") String bearerToken, @PathVariable String idPersona, @PathVariable Integer idCargo) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<InfoLaboral> infoLaboral = infoLaboralRepository.findFirstById_IdPersonaAndId_IdCargoOrderById_IdPersonaAsc(idPersona, idCargo);
            if (infoLaboral.isPresent()) {
                infoLaboralRepository.deleteById(infoLaboral.get().getId());
                return ResponseEntity.ok(infoLaboral.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
