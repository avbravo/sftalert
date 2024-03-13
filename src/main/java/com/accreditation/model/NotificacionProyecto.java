/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity
public class NotificacionProyecto {
    @Id
    private Long idNotifiacionProyecto;
    @Column
    private String notificacion;
    @Column
    private String titulo;
    @Column
    private Long iduser;
    @Column
    private Long iduserEmisor;
    @Column
    private Long idproyecto;
    
    @Column
    private Boolean visto;
    
    @Column
    private Date fecha;
    

    public NotificacionProyecto() {
    }

    public NotificacionProyecto(Long idNotifiacionProyecto, String notificacion, String titulo, Long iduser, Long iduserEmisor, Long idproyecto, Boolean visto, Date fecha) {
        this.idNotifiacionProyecto = idNotifiacionProyecto;
        this.notificacion = notificacion;
        this.titulo = titulo;
        this.iduser = iduser;
        this.iduserEmisor = iduserEmisor;
        this.idproyecto = idproyecto;
        this.visto = visto;
        this.fecha = fecha;
    }

    public Long getIdNotifiacionProyecto() {
        return idNotifiacionProyecto;
    }

    public void setIdNotifiacionProyecto(Long idNotifiacionProyecto) {
        this.idNotifiacionProyecto = idNotifiacionProyecto;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getIduser() {
        return iduser;
    }

    public void setIduser(Long iduser) {
        this.iduser = iduser;
    }

    public Long getIduserEmisor() {
        return iduserEmisor;
    }

    public void setIduserEmisor(Long iduserEmisor) {
        this.iduserEmisor = iduserEmisor;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Boolean getVisto() {
        return visto;
    }

    public void setVisto(Boolean visto) {
        this.visto = visto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NotificacionProyecto{");
        sb.append("idNotifiacionProyecto=").append(idNotifiacionProyecto);
        sb.append(", notificacion=").append(notificacion);
        sb.append(", titulo=").append(titulo);
        sb.append(", iduser=").append(iduser);
        sb.append(", iduserEmisor=").append(iduserEmisor);
        sb.append(", idproyecto=").append(idproyecto);
        sb.append(", visto=").append(visto);
        sb.append(", fecha=").append(fecha);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.idNotifiacionProyecto);
        hash = 47 * hash + Objects.hashCode(this.notificacion);
        hash = 47 * hash + Objects.hashCode(this.titulo);
        hash = 47 * hash + Objects.hashCode(this.iduser);
        hash = 47 * hash + Objects.hashCode(this.iduserEmisor);
        hash = 47 * hash + Objects.hashCode(this.idproyecto);
        hash = 47 * hash + Objects.hashCode(this.visto);
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
        final NotificacionProyecto other = (NotificacionProyecto) obj;
        if (!Objects.equals(this.notificacion, other.notificacion)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.idNotifiacionProyecto, other.idNotifiacionProyecto)) {
            return false;
        }
        if (!Objects.equals(this.iduser, other.iduser)) {
            return false;
        }
        if (!Objects.equals(this.iduserEmisor, other.iduserEmisor)) {
            return false;
        }
        if (!Objects.equals(this.idproyecto, other.idproyecto)) {
            return false;
        }
        if (!Objects.equals(this.visto, other.visto)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    
    
}
