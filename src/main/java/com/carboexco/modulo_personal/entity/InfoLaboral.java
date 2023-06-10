package com.carboexco.modulo_personal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "info_laboral")
public class InfoLaboral {
    @EmbeddedId
    private InfoLaboralId id;

    @Column(name = "id_hoja_vida")
    private Integer idHojaVida;

    @Column(name = "id_acta_retiro")
    private Integer idActaRetiro;

    @Column(name = "estado_laboral", nullable = false)
    private Boolean estadoLaboral = false;

    @Column(name = "afiliacion_seguridad_social", length = 50)
    private String afiliacionSeguridadSocial;

    @Column(name = "eps", length = 50)
    private String eps;

    @Column(name = "arl", length = 50)
    private String arl;

    @Column(name = "caja_compensacion_familiar", length = 50)
    private String cajaCompensacionFamiliar;

    @Column(name = "pensiones", length = 50)
    private String pensiones;

    @Column(name = "motivo_retiro", length = 1000)
    private String motivoRetiro;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_retiro")
    private LocalDate fechaRetiro;

}