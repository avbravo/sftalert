/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Etiqueta {
    @Column
    private String etiqueta;
    @Column
    private Boolean active;
    
    @Column(commentary = " , primary, success,info, warning,danger ")
    private String severity;

    public Etiqueta() {
    }

    public Etiqueta(String etiqueta, Boolean active, String severity) {
        this.etiqueta = etiqueta;
        this.active = active;
        this.severity = severity;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Etiqueta{");
        sb.append("etiqueta=").append(etiqueta);
        sb.append(", active=").append(active);
        sb.append(", severity=").append(severity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.etiqueta);
        hash = 67 * hash + Objects.hashCode(this.active);
        hash = 67 * hash + Objects.hashCode(this.severity);
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
        final Etiqueta other = (Etiqueta) obj;
        if (!Objects.equals(this.etiqueta, other.etiqueta)) {
            return false;
        }
        if (!Objects.equals(this.severity, other.severity)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    
    
    
    
}
