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
public class SprintOpen {

    @Column
    private Long idpsrint;
    @Column
    private String sprint;
    @Column
    private Date fechainicial;
    @Column
    private Date fechafinal;

    @Column
    private Boolean open;

    public SprintOpen() {
    }

    
    
    
    
    
    public SprintOpen(Long idpsrint, String sprint, Date fechainicial, Date fechafinal, Boolean open) {
        this.idpsrint = idpsrint;
        this.sprint = sprint;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.open = open;
    }

    public Long getIdpsrint() {
        return idpsrint;
    }

    public void setIdpsrint(Long idpsrint) {
        this.idpsrint = idpsrint;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public Date getFechainicial() {
        return fechainicial;
    }

    public void setFechainicial(Date fechainicial) {
        this.fechainicial = fechainicial;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SprintOpen other = (SprintOpen) obj;
        if (!Objects.equals(this.sprint, other.sprint)) {
            return false;
        }
        if (!Objects.equals(this.idpsrint, other.idpsrint)) {
            return false;
        }
        if (!Objects.equals(this.fechainicial, other.fechainicial)) {
            return false;
        }
        if (!Objects.equals(this.fechafinal, other.fechafinal)) {
            return false;
        }
        return Objects.equals(this.open, other.open);
    }

    @Override
    public String toString() {
        return "SprintOpen{" + "idpsrint=" + idpsrint + ", sprint=" + sprint + ", fechainicial=" + fechainicial + ", fechafinal=" + fechafinal + ", open=" + open + '}';
    }
   

}
