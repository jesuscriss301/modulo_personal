package com.carboexco.modulo_personal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "tipo_cargo", nullable = false, length = 20)
    private String tipoCargo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departamento", nullable = false)
    private Departamento departamento;

    @Column(name = "area", nullable = false)
    private Integer area;

}