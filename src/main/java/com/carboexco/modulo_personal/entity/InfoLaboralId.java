package com.carboexco.modulo_personal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class InfoLaboralId implements Serializable {
    private static final long serialVersionUID = -4885165163224914903L;
    @Column(name = "id_persona", nullable = false, length = 7)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona idPersona;

    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InfoLaboralId entity = (InfoLaboralId) o;
        return Objects.equals(this.idCargo, entity.idCargo) &&
                Objects.equals(this.idPersona, entity.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCargo, idPersona);
    }

}