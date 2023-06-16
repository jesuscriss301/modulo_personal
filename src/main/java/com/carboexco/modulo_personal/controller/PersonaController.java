package com.carboexco.modulo_personal.controller;
import com.carboexco.modulo_personal.entity.InfoLaboral;
import com.carboexco.modulo_personal.entity.Persona;
import com.carboexco.modulo_personal.repository.InfoLaboralRepository;
import com.carboexco.modulo_personal.repository.PersonaRepository;
import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    InfoLaboralRepository laboralRepository;

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> getPersonaAll() {
        return personaRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getPersonabyId(@PathVariable String id) {
        String []ids=  id.split(",");
        StringBuffer nombres=new StringBuffer("");
        for(int i =0;i<ids.length; i++) {
            ids[i] = ids[i].replace(" ", "");
            Optional<Persona> persona = personaRepository.findById(ids[i]);
            if (persona.isPresent()){
                nombres.append(persona.get().getPrimerNombre()+" "+persona.get().getApellidoPaterno());
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

        List<Persona> personasCargo=new ArrayList<>();
        List<InfoLaboral> infoLaborals = (List<InfoLaboral>) laboralRepository.findFirstById_IdCargoOrderById_IdPersonaAsc(id);
        for (InfoLaboral i: infoLaborals){
            personasCargo.add(i.getId().getIdPersona());
        }
        return personasCargo;
    }

    @PostMapping
    public Persona postPersona(@RequestBody Persona persona) {
        personaRepository.save(persona);
        return persona;
    }

    @PutMapping("/{id}")
    public Persona putPersonabyId(@PathVariable String id, @RequestBody Persona persona) {

        Optional<Persona> personaCurrent = personaRepository.findById(id);

        if (personaCurrent.isPresent()) {
            Persona personaReturn = personaCurrent.get();

            personaReturn.setPrimerNombre(persona.getPrimerNombre());
            personaReturn.setSegundoNombre(persona.getSegundoNombre());
            personaReturn.setApellidoPaterno(persona.getApellidoPaterno());
            personaReturn.setApellidoMaterno(persona.getApellidoMaterno());
            personaReturn.setCedula(persona.getCedula());
            personaReturn.setTelefono(persona.getTelefono());
            personaReturn.setIdFoto(personaReturn.getIdFoto());

            return personaReturn;

        }
        return null;
    }
}