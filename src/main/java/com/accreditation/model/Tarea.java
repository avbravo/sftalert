/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Tarea {

    @Column
    private String tarea;
    @Column
    private Boolean completado;
    @Column
    private Boolean active;
    @Column
    private Integer orden;
    @ViewReferenced(from = "user", localField = "iduser")
    private UserView userView;

    public Tarea() {
    }

    public Tarea(String tarea, Boolean completado, Boolean active, Integer orden, UserView userView) {
        this.tarea = tarea;
        this.completado = completado;
        this.active = active;
        this.orden = orden;
        this.userView = userView;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.tarea);
        hash = 41 * hash + Objects.hashCode(this.completado);
        hash = 41 * hash + Objects.hashCode(this.active);
        hash = 41 * hash + Objects.hashCode(this.orden);
        hash = 41 * hash + Objects.hashCode(this.userView);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tarea other = (Tarea) obj;
        if (!Objects.equals(this.tarea, other.tarea)) {
            return false;
        }
        if (!Objects.equals(this.completado, other.completado)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.orden, other.orden)) {
            return false;
        }
        return Objects.equals(this.userView, other.userView);
    }

    @Override
    public String toString() {
        return "Tarea{" + "tarea=" + tarea + ", completado=" + completado + ", active=" + active + ", orden=" + orden + ", userView=" + userView + '}';
    }

    
}
