/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class EstadisticaCierreColaborador {
    @Column
    private Long iduser;
    @Column
    private Integer pendiente;
    @Column
    private Integer progreso;
    @Column
    private Integer finalizado;
    @Column
    private Double avance;



    public EstadisticaCierreColaborador() {
    }

    public EstadisticaCierreColaborador(Long iduser, Integer pendiente, Integer progreso, Integer finalizado, Double avance) {
        this.iduser = iduser;
        this.pendiente = pendiente;
        this.progreso = progreso;
        this.finalizado = finalizado;
        this.avance = avance;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Integer getPendiente() {
        return pendiente;
    }

    public void setPendiente(Integer pendiente) {
        this.pendiente = pendiente;
    }

    public Integer getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progreso) {
        this.progreso = progreso;
    }

    public Integer getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Integer finalizado) {
        this.finalizado = finalizado;
    }

    public Double getAvance() {
        return avance;
    }

    public void setAvance(Double avance) {
        this.avance = avance;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.iduser);
        hash = 41 * hash + Objects.hashCode(this.pendiente);
        hash = 41 * hash + Objects.hashCode(this.progreso);
        hash = 41 * hash + Objects.hashCode(this.finalizado);
        hash = 41 * hash + Objects.hashCode(this.avance);
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
        final EstadisticaCierreColaborador other = (EstadisticaCierreColaborador) obj;
        if (!Objects.equals(this.iduser, other.iduser)) {
            return false;
        }
        if (!Objects.equals(this.pendiente, other.pendiente)) {
            return false;
        }
        if (!Objects.equals(this.progreso, other.progreso)) {
            return false;
        }
        if (!Objects.equals(this.finalizado, other.finalizado)) {
            return false;
        }
        return Objects.equals(this.avance, other.avance);
    }

    @Override
    public String toString() {
        return "EstadisticaCierreColaborador{" + "iduser=" + iduser + ", pendiente=" + pendiente + ", progreso=" + progreso + ", finalizado=" + finalizado + ", avance=" + avance + '}';
    }

    
    

}
