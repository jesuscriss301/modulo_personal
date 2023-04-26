package com.carboexco.modulo_personal.controller;
import com.carboexco.modulo_personal.entity.Persona;
import com.carboexco.modulo_personal.repository.PersonaRepository;
import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> getPersonaAll() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getPersonabyId(@PathVariable String id) {
        int []ids=  convertirStringAEnteros(id);
        StringBuffer nombres=new StringBuffer("");
        for(int i =0;i<ids.length; i++) {
            Optional<Persona> persona = personaRepository.findById(ids[i]);

            if (persona.isPresent()){
                nombres.append(persona.get().getNombres() +" "+persona.get().getApellidos());
                if (i<ids.length-1){
                    nombres.append(", ");
                }
            }

        }
        return nombres.toString();
    }

    private int[] convertirStringAEnteros(String cadena) {
        // Eliminar los espacios en blanco al principio y al final de la cadena
        cadena = cadena.trim();

        // Si la cadena está vacía, retornar un arreglo vacío
        if (cadena.isEmpty()) {
            return new int[0];
        }

        // Dividir la cadena en una matriz de cadenas utilizando la coma como separador
        String[] partes = cadena.split(",");

        // Crear un arreglo de enteros con la misma longitud que la matriz de cadenas
        int[] numeros = new int[partes.length];

        // Convertir cada cadena en un entero y agregarlo al arreglo de enteros
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i].trim());
        }

        // Retornar el arreglo de enteros
        return numeros;
    }

    @GetMapping("/cargo/{id}")
    public List<Persona> getPersonabyCargo(@PathVariable int id) {

        List<Persona> personasCargo = personaRepository.findByIdCargo_Id(id);
        return personasCargo;
    }

    @PostMapping
    public Persona postPersona(@RequestBody Persona persona) {
        personaRepository.save(persona);
        return persona;
    }

    @PutMapping("/{id}")
    public Persona putPersonabyId(@PathVariable int id, @RequestBody Persona persona) {

        Optional<Persona> personaCurrent = personaRepository.findById(id);

        if (personaCurrent.isPresent()) {
            Persona personaReturn = personaCurrent.get();

            personaReturn.setNombres(persona.getNombres());
            personaReturn.setApellidos(persona.getApellidos());
            personaReturn.setCedula(persona.getCedula());
            personaReturn.setIdCargo(persona.getIdCargo());


            return personaReturn;

        }
        return null;
    }
}