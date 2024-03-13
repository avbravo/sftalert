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
public class ActionHistory {

   @Column
    private Date fecha;

    @Column
    private Long iduser;

    @Column
    private String evento;
    
    @Column(commentary = "Clase desde la que se invoca el evento")
    private String clase;
    
    @Column(commentary = "Metodo que invoca el eventeo")
    private String metodo;

    public ActionHistory() {
    }

    public ActionHistory(Date fecha, Long iduser, String evento, String clase, String metodo) {
        this.fecha = fecha;
        this.iduser = iduser;
        this.evento = evento;
        this.clase = clase;
        this.metodo = metodo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActionHistory{");
        sb.append("fecha=").append(fecha);
        sb.append(", iduser=").append(iduser);
        sb.append(", evento=").append(evento);
        sb.append(", clase=").append(clase);
        sb.append(", metodo=").append(metodo);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.iduser);
        hash = 67 * hash + Objects.hashCode(this.evento);
        hash = 67 * hash + Objects.hashCode(this.clase);
        hash = 67 * hash + Objects.hashCode(this.metodo);
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
        final ActionHistory other = (ActionHistory) obj;
        if (!Objects.equals(this.evento, other.evento)) {
            return false;
        }
        if (!Objects.equals(this.clase, other.clase)) {
            return false;
        }
        if (!Objects.equals(this.metodo, other.metodo)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return Objects.equals(this.iduser, other.iduser);
    }

    
    

    public static class Builder {

        private Date fecha;
        private Long iduser;
        private String evento;
            private String clase;
            private String metodo;

        public Builder fecha(Date fecha) {
            this.fecha= fecha;
            return this;
        }

        public Builder iduser(Long iduser) {
            this.iduser = iduser;
            return this;
        }
        public Builder evento(String evento) {
            this.evento = evento;
            return this;
        }
        public Builder clase(String clase) {
            this.clase = clase;
            return this;
        }
        public Builder metodo(String metodo) {
            this.metodo= metodo;
            return this;
        }

        public ActionHistory build() {
            return new ActionHistory(fecha, iduser, evento, clase, metodo);
        }

    }
}
