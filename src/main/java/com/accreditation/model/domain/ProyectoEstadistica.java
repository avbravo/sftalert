/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model.domain;

import com.jmoordb.core.annotation.Domain;

/**
 *
 * @author avbravo
 */
@Domain(commentary = "Se usa para calular los totales del proyecto")
public class ProyectoEstadistica {
    private Integer totalSprint;
    private Integer totalTarjetasBacklog;
    private Integer totalTarjetasPendiente;
    private Integer totalTarjetasProgreso;
    private Integer totalTarjetasFinalizado;
    private Long idproyecto;

    public ProyectoEstadistica() {
    }

    public ProyectoEstadistica(Integer totalSprint, Integer totalTarjetasBacklog, Integer totalTarjetasPendiente, Integer totalTarjetasProgreso, Integer totalTarjetasFinalizado, Long idproyecto) {
        this.totalSprint = totalSprint;
        this.totalTarjetasBacklog = totalTarjetasBacklog;
        this.totalTarjetasPendiente = totalTarjetasPendiente;
        this.totalTarjetasProgreso = totalTarjetasProgreso;
        this.totalTarjetasFinalizado = totalTarjetasFinalizado;
        this.idproyecto = idproyecto;
    }

    public Integer getTotalSprint() {
        return totalSprint;
    }

    public void setTotalSprint(Integer totalSprint) {
        this.totalSprint = totalSprint;
    }

    public Integer getTotalTarjetasBacklog() {
        return totalTarjetasBacklog;
    }

    public void setTotalTarjetasBacklog(Integer totalTarjetasBacklog) {
        this.totalTarjetasBacklog = totalTarjetasBacklog;
    }

    public Integer getTotalTarjetasPendiente() {
        return totalTarjetasPendiente;
    }

    public void setTotalTarjetasPendiente(Integer totalTarjetasPendiente) {
        this.totalTarjetasPendiente = totalTarjetasPendiente;
    }

    public Integer getTotalTarjetasProgreso() {
        return totalTarjetasProgreso;
    }

    public void setTotalTarjetasProgreso(Integer totalTarjetasProgreso) {
        this.totalTarjetasProgreso = totalTarjetasProgreso;
    }

    public Integer getTotalTarjetasFinalizado() {
        return totalTarjetasFinalizado;
    }

    public void setTotalTarjetasFinalizado(Integer totalTarjetasFinalizado) {
        this.totalTarjetasFinalizado = totalTarjetasFinalizado;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Override
    public String toString() {
        return "ProyectoEstadistica{" + "totalSprint=" + totalSprint + ", totalTarjetasBacklog=" + totalTarjetasBacklog + ", totalTarjetasPendiente=" + totalTarjetasPendiente + ", totalTarjetasProgreso=" + totalTarjetasProgreso + ", totalTarjetasFinalizado=" + totalTarjetasFinalizado + ", idproyecto=" + idproyecto + '}';
    }

    
   

  
    
    
    
    
    
}
