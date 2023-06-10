package com.carboexco.modulo_personal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
public class Persona {


    @Id
    @Column(name = "id_persona", nullable = false, length = 11)
    private String idPersona;

    @Column(name = "primer_nombre", nullable = false, length = 20)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 20)
    private String segundoNombre;

    @Column(name = "apellido_paterno", nullable = false, length = 20)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 20)
    private String apellidoMaterno;

    @Column(name = "cedula", length = 15)
    private String cedula;

    @Column(name = "telefono", length = 10)
    private String telefono;

    @Column(name = "id_foto")
    private Integer idFoto;

}