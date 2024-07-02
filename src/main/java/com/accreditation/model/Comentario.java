/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Embedded;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Comentario {
    @Column
    private String comentario;
    
    @Column(commentary = "Se utiliza para evitar que el solicitante foreano vea algunos comentarios en la tarjeta")
    private Boolean privado;

    

    @Column
    private Date fecha;
    @Column
    private Boolean active;
    @Embedded
    private UserView userView;

    public Comentario() {
    }
public Comentario(String comentario, Boolean privado, Date fecha, Boolean active, UserView userView) {
        this.comentario = comentario;
        this.privado = privado;
        this.fecha = fecha;
        this.active = active;
        this.userView = userView;
    }
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comentario{");
        sb.append("comentario=").append(comentario);
        sb.append(", privado=").append(privado);
        sb.append(", fecha=").append(fecha);
        sb.append(", active=").append(active);
        sb.append(", userView=").append(userView);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.comentario);
        hash = 67 * hash + Objects.hashCode(this.privado);
        hash = 67 * hash + Objects.hashCode(this.fecha);
        hash = 67 * hash + Objects.hashCode(this.active);
        hash = 67 * hash + Objects.hashCode(this.userView);
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
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.comentario, other.comentario)) {
            return false;
        }
        if (!Objects.equals(this.privado, other.privado)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        return Objects.equals(this.userView, other.userView);
    }

    
    
}
