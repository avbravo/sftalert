/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Applicativeprivilege {

    @Ignore
    private Long id;
     
    @Column
    private String event;
      @Column 
    private Boolean create;
   
    @Column 
    private Boolean read;
   
    @Column 
    private Boolean update;
   
    @Column 
    private Boolean delete;
   
    
 @Column
    private Boolean active;
 @Column
    private Long idrole;

   
  

    public Applicativeprivilege() {
    }

    public Applicativeprivilege(Long id, String event, Boolean create, Boolean read, Boolean update, Boolean delete, Boolean active, Long idrole) {
        this.id = id;
        this.event = event;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.active = active;
        this.idrole = idrole;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getIdrole() {
        return idrole;
    }

    public void setIdrole(Long idrole) {
        this.idrole = idrole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Applicativeprivilege{");
        sb.append("id=").append(id);
        sb.append(", event=").append(event);
        sb.append(", create=").append(create);
        sb.append(", read=").append(read);
        sb.append(", update=").append(update);
        sb.append(", delete=").append(delete);
        sb.append(", active=").append(active);
        sb.append(", idrole=").append(idrole);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.event);
        hash = 13 * hash + Objects.hashCode(this.create);
        hash = 13 * hash + Objects.hashCode(this.read);
        hash = 13 * hash + Objects.hashCode(this.update);
        hash = 13 * hash + Objects.hashCode(this.delete);
        hash = 13 * hash + Objects.hashCode(this.active);
        hash = 13 * hash + Objects.hashCode(this.idrole);
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
        final Applicativeprivilege other = (Applicativeprivilege) obj;
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.create, other.create)) {
            return false;
        }
        if (!Objects.equals(this.read, other.read)) {
            return false;
        }
        if (!Objects.equals(this.update, other.update)) {
            return false;
        }
        if (!Objects.equals(this.delete, other.delete)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        return Objects.equals(this.idrole, other.idrole);
    }

    

}