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
public class EstadisticaCierre {

    @Column
    private String comentario;
    @Column
    private Integer pendiente;
    @Column
    private Integer progreso;
    @Column
    private Integer finalizado;
    @Column
    private Double avance;

    @Column
    private Date fecha;

    public EstadisticaCierre() {
    }

    public EstadisticaCierre(String comentario, Integer pendiente, Integer progreso, Integer finalizado, Double avance, Date fecha) {
        this.comentario = comentario;
        this.pendiente = pendiente;
        this.progreso = progreso;
        this.finalizado = finalizado;
        this.avance = avance;
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.comentario);
        hash = 47 * hash + Objects.hashCode(this.pendiente);
        hash = 47 * hash + Objects.hashCode(this.progreso);
        hash = 47 * hash + Objects.hashCode(this.finalizado);
        hash = 47 * hash + Objects.hashCode(this.avance);
        hash = 47 * hash + Objects.hashCode(this.fecha);
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
        final EstadisticaCierre other = (EstadisticaCierre) obj;
        if (!Objects.equals(this.comentario, other.comentario)) {
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
        if (!Objects.equals(this.avance, other.avance)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    @Override
    public String toString() {
        return "EstadisticaCierre{" + "comentario=" + comentario + ", pendiente=" + pendiente + ", progreso=" + progreso + ", finalizado=" + finalizado + ", avance=" + avance + ", fecha=" + fecha + '}';
    }

  

}
