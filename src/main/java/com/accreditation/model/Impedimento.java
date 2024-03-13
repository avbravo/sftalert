/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Impedimento {
    @Column
    private String impedimento;
  
     @Column
    private Date fecha;
       @Column
    private Boolean completado;
      @ViewReferenced(from = "user", localField = "iduser")
    private UserView userView;  
    @Column
    private Boolean active;

  public Impedimento() {
    }

    public Impedimento(String impedimento, Date fecha, Boolean completado, UserView userView, Boolean active) {
        this.impedimento = impedimento;
        this.fecha = fecha;
        this.completado = completado;
        this.userView = userView;
        this.active = active;
    }

    public String getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(String impedimento) {
        this.impedimento = impedimento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.impedimento);
        hash = 43 * hash + Objects.hashCode(this.fecha);
        hash = 43 * hash + Objects.hashCode(this.completado);
        hash = 43 * hash + Objects.hashCode(this.userView);
        hash = 43 * hash + Objects.hashCode(this.active);
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
        final Impedimento other = (Impedimento) obj;
        if (!Objects.equals(this.impedimento, other.impedimento)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.completado, other.completado)) {
            return false;
        }
        if (!Objects.equals(this.userView, other.userView)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    @Override
    public String toString() {
        return "Impedimento{" + "impedimento=" + impedimento + ", fecha=" + fecha + ", completado=" + completado + ", userView=" + userView + ", active=" + active + '}';
    }

  
  
   
    
}
