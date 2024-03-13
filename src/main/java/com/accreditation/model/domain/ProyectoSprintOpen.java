/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model.domain;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Domain;
import com.accreditation.model.Proyecto;
import com.accreditation.model.SprintOpen;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Domain
public class ProyectoSprintOpen {
    @Column
    private Proyecto proyecto;
    @Column
    private SprintOpen sprintOpen;

    public ProyectoSprintOpen() {
    }

    public ProyectoSprintOpen(Proyecto proyecto, SprintOpen sprintOpen) {
        this.proyecto = proyecto;
        this.sprintOpen = sprintOpen;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public SprintOpen getSprintOpen() {
        return sprintOpen;
    }

    public void setSprintOpen(SprintOpen sprintOpen) {
        this.sprintOpen = sprintOpen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.proyecto);
        hash = 97 * hash + Objects.hashCode(this.sprintOpen);
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
        final ProyectoSprintOpen other = (ProyectoSprintOpen) obj;
        if (!Objects.equals(this.proyecto, other.proyecto)) {
            return false;
        }
        return Objects.equals(this.sprintOpen, other.sprintOpen);
    }

    @Override
    public String toString() {
        return "ProyectoSprintOpen{" + "proyecto=" + proyecto + ", sprintOpen=" + sprintOpen + '}';
    }
    
    
    
    
}
