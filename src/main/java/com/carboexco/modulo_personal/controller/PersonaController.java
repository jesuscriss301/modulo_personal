package com.carboexco.modulo_personal.controller;

import com.carboexco.modulo_personal.entity.Persona;
import com.carboexco.modulo_personal.repository.InfoLaboralRepository;
import com.carboexco.modulo_personal.repository.PersonaRepository;
import com.carboexco.modulo_personal.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PersonaController {

    private final InfoLaboralRepository laboralRepository;
    private final PersonaRepository personaRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @Autowired
    public PersonaController(InfoLaboralRepository laboralRepository, PersonaRepository personaRepository) {
        this.laboralRepository = laboralRepository;
        this.personaRepository = personaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Persona>> getPersonaAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Persona> personas = personaRepository.findAll();
            return ResponseEntity.ok(personas);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPersonabyId(@RequestHeader("Authorization") String bearerToken, @PathVariable String id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            String[] ids = id.split(",");
            StringBuffer nombres = new StringBuffer("");
            for (int i = 0; i < ids.length; i++) {
                ids[i] = ids[i].replace(" ", "");
                Optional<Persona> persona = personaRepository.findById(ids[i]);
                if (persona.isPresent()) {
                    nombres.append(persona.get().getPrimerNombre() + " " + persona.get().getApellidoPaterno());
                    if (i < ids.length - 1) {
                        nombres.append(", ");
                    }
                }
            }
            return ResponseEntity.ok(nombres.toString());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Resto del código...

    @PostMapping
    public ResponseEntity<Persona> postPersona(@RequestHeader("Authorization") String bearerToken, @RequestBody Persona persona) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Persona createdPersona = personaRepository.save(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPersona);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> putPersonabyId(@RequestHeader("Authorization") String bearerToken, @PathVariable String id, @RequestBody Persona persona) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Persona> personaCurrent = personaRepository.findById(id);
            if (personaCurrent.isPresent()) {
                Persona personaReturn = personaCurrent.get();
                personaReturn.setPrimerNombre(persona.getPrimerNombre());
                personaReturn.setSegundoNombre(persona.getSegundoNombre());
                personaReturn.setApellidoPaterno(persona.getApellidoPaterno());
                personaReturn.setApellidoMaterno(persona.getApellidoMaterno());
                personaReturn.setCedula(persona.getCedula());
                personaReturn.setTelefono(persona.getTelefono());
                personaReturn.setIdFoto(persona.getIdFoto());
                Persona updatedPersona = personaRepository.save(personaReturn);
                return ResponseEntity.ok(updatedPersona);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}