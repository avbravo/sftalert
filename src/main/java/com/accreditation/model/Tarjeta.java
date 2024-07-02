/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.Embedded;
import com.jmoordb.core.annotation.Entity;
import com.jmoordb.core.annotation.Id;
import com.jmoordb.core.annotation.Index;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.ViewReferenced;
import com.jmoordb.core.annotation.enumerations.AutogeneratedActive;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@Entity
@Index(name = "tarjeta_idx", fieldNames = {"id", "name"})
public class Tarjeta {

    @Id(autogeneratedActive = AutogeneratedActive.ON)
    private Long idtarjeta;
    @Column(generateQuery = true)
    private String tarjeta;
    @Column(generateQuery = true)
    private String descripcion;

//    @ViewReferenced(from = "user", localField = "iduser")
    @Embedded
    List<UserView> userView;

    @Column(generateQuery = true)
    private Date fechainicial;
    @Column(generateQuery = true)
    private Date fechafinal;
//    @Referenced(from = "icono", localField = "idicono", commentary = "Esta asociado a la prioridad")
    @Embedded
    private Icono icono;
//    @Referenced(from = "tipotarjeta", localField = "idtipotarjeta", commentary = "Ayuda para la implementación de Deep Learning")
    @Embedded
    private Tipotarjeta tipotarjeta;

    @Column(generateQuery = true)
    private Long idsprint;

    @Column(generateQuery = true)
    private Long idproyecto;

    @Column(generateQuery = true)
    private Boolean backlog;

    @Column(commentary = "")
    private Boolean programado;

    @Column(generateQuery = true, commentary = "alta,baja,media")
    private String prioridad;

    @Column(generateQuery = true)
    private String estimacion;

    @Column(generateQuery = true, commentary = "pendiente,progreso,finalizado")
    private String columna;
    @Column(generateQuery = true)
    private Boolean active;

    @Embedded
    List<Tarea> tarea;

    @Embedded
    List<Comentario> comentario;

    @Embedded
    List<Etiqueta> etiqueta;

    @Embedded
    List<Archivo> archivo;

    @Embedded
    List<Impedimento> impedimento;

    @Column(generateQuery = true, commentary = "true cuando la crea un colaborador que no pertenece al proyecto y es un proyecto publico")
    private Boolean foreaneo;

    @Column(generateQuery = true)
    private Date lastModification;

    @Embedded
    List<ActionHistory> actionHistory;

    public Tarjeta() {
    }

    public Tarjeta(Long idtarjeta, String tarjeta, String descripcion, List<UserView> userView, Date fechainicial, Date fechafinal, Icono icono, Tipotarjeta tipotarjeta, Long idsprint, Long idproyecto, Boolean backlog, Boolean programado, String prioridad, String estimacion, String columna, Boolean active, List<Tarea> tarea, List<Comentario> comentario, List<Etiqueta> etiqueta, List<Archivo> archivo, List<Impedimento> impedimento, Boolean foreaneo, Date lastModification, List<ActionHistory> actionHistory) {
        this.idtarjeta = idtarjeta;
        this.tarjeta = tarjeta;
        this.descripcion = descripcion;
        this.userView = userView;
        this.fechainicial = fechainicial;
        this.fechafinal = fechafinal;
        this.icono = icono;
        this.tipotarjeta = tipotarjeta;
        this.idsprint = idsprint;
        this.idproyecto = idproyecto;
        this.backlog = backlog;
        this.programado = programado;
        this.prioridad = prioridad;
        this.estimacion = estimacion;
        this.columna = columna;
        this.active = active;
        this.tarea = tarea;
        this.comentario = comentario;
        this.etiqueta = etiqueta;
        this.archivo = archivo;
        this.impedimento = impedimento;
        this.foreaneo = foreaneo;
        this.lastModification = lastModification;
        this.actionHistory = actionHistory;
    }

    public Long getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta(Long idtarjeta) {
        this.idtarjeta = idtarjeta;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<UserView> getUserView() {
        return userView;
    }

    public void setUserView(List<UserView> userView) {
        this.userView = userView;
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

    public Icono getIcono() {
        return icono;
    }

    public void setIcono(Icono icono) {
        this.icono = icono;
    }

    public Tipotarjeta getTipotarjeta() {
        return tipotarjeta;
    }

    public void setTipotarjeta(Tipotarjeta tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    public Long getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(Long idsprint) {
        this.idsprint = idsprint;
    }

    public Long getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(Long idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Boolean getBacklog() {
        return backlog;
    }

    public void setBacklog(Boolean backlog) {
        this.backlog = backlog;
    }

    public Boolean getProgramado() {
        return programado;
    }

    public void setProgramado(Boolean programado) {
        this.programado = programado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstimacion() {
        return estimacion;
    }

    public void setEstimacion(String estimacion) {
        this.estimacion = estimacion;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Tarea> getTarea() {
        return tarea;
    }

    public void setTarea(List<Tarea> tarea) {
        this.tarea = tarea;
    }

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }

    public List<Etiqueta> getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(List<Etiqueta> etiqueta) {
        this.etiqueta = etiqueta;
    }

    public List<Archivo> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<Archivo> archivo) {
        this.archivo = archivo;
    }

    public List<Impedimento> getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(List<Impedimento> impedimento) {
        this.impedimento = impedimento;
    }

    public Boolean getForeaneo() {
        return foreaneo;
    }

    public void setForeaneo(Boolean foreaneo) {
        this.foreaneo = foreaneo;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public List<ActionHistory> getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(List<ActionHistory> actionHistory) {
        this.actionHistory = actionHistory;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.idtarjeta);
        hash = 43 * hash + Objects.hashCode(this.tarjeta);
        hash = 43 * hash + Objects.hashCode(this.descripcion);
        hash = 43 * hash + Objects.hashCode(this.userView);
        hash = 43 * hash + Objects.hashCode(this.fechainicial);
        hash = 43 * hash + Objects.hashCode(this.fechafinal);
        hash = 43 * hash + Objects.hashCode(this.icono);
        hash = 43 * hash + Objects.hashCode(this.tipotarjeta);
        hash = 43 * hash + Objects.hashCode(this.idsprint);
        hash = 43 * hash + Objects.hashCode(this.idproyecto);
        hash = 43 * hash + Objects.hashCode(this.backlog);
        hash = 43 * hash + Objects.hashCode(this.programado);
        hash = 43 * hash + Objects.hashCode(this.prioridad);
        hash = 43 * hash + Objects.hashCode(this.estimacion);
        hash = 43 * hash + Objects.hashCode(this.columna);
        hash = 43 * hash + Objects.hashCode(this.active);
        hash = 43 * hash + Objects.hashCode(this.tarea);
        hash = 43 * hash + Objects.hashCode(this.comentario);
        hash = 43 * hash + Objects.hashCode(this.etiqueta);
        hash = 43 * hash + Objects.hashCode(this.archivo);
        hash = 43 * hash + Objects.hashCode(this.impedimento);
        hash = 43 * hash + Objects.hashCode(this.foreaneo);
        hash = 43 * hash + Objects.hashCode(this.lastModification);
        hash = 43 * hash + Objects.hashCode(this.actionHistory);
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
        final Tarjeta other = (Tarjeta) obj;
        if (!Objects.equals(this.tarjeta, other.tarjeta)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.prioridad, other.prioridad)) {
            return false;
        }
        if (!Objects.equals(this.estimacion, other.estimacion)) {
            return false;
        }
        if (!Objects.equals(this.columna, other.columna)) {
            return false;
        }
        if (!Objects.equals(this.idtarjeta, other.idtarjeta)) {
            return false;
        }
        if (!Objects.equals(this.userView, other.userView)) {
            return false;
        }
        if (!Objects.equals(this.fechainicial, other.fechainicial)) {
            return false;
        }
        if (!Objects.equals(this.fechafinal, other.fechafinal)) {
            return false;
        }
        if (!Objects.equals(this.icono, other.icono)) {
            return false;
        }
        if (!Objects.equals(this.tipotarjeta, other.tipotarjeta)) {
            return false;
        }
        if (!Objects.equals(this.idsprint, other.idsprint)) {
            return false;
        }
        if (!Objects.equals(this.idproyecto, other.idproyecto)) {
            return false;
        }
        if (!Objects.equals(this.backlog, other.backlog)) {
            return false;
        }
        if (!Objects.equals(this.programado, other.programado)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.tarea, other.tarea)) {
            return false;
        }
        if (!Objects.equals(this.comentario, other.comentario)) {
            return false;
        }
        if (!Objects.equals(this.etiqueta, other.etiqueta)) {
            return false;
        }
        if (!Objects.equals(this.archivo, other.archivo)) {
            return false;
        }
        if (!Objects.equals(this.impedimento, other.impedimento)) {
            return false;
        }
        if (!Objects.equals(this.foreaneo, other.foreaneo)) {
            return false;
        }
        if (!Objects.equals(this.lastModification, other.lastModification)) {
            return false;
        }
        return Objects.equals(this.actionHistory, other.actionHistory);
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "idtarjeta=" + idtarjeta + ", tarjeta=" + tarjeta + ", descripcion=" + descripcion + ", userView=" + userView + ", fechainicial=" + fechainicial + ", fechafinal=" + fechafinal + ", icono=" + icono + ", tipotarjeta=" + tipotarjeta + ", idsprint=" + idsprint + ", idproyecto=" + idproyecto + ", backlog=" + backlog + ", programado=" + programado + ", prioridad=" + prioridad + ", estimacion=" + estimacion + ", columna=" + columna + ", active=" + active + ", tarea=" + tarea + ", comentario=" + comentario + ", etiqueta=" + etiqueta + ", archivo=" + archivo + ", impedimento=" + impedimento + ", foreaneo=" + foreaneo + ", lastModification=" + lastModification + ", actionHistory=" + actionHistory + '}';
    }

}
