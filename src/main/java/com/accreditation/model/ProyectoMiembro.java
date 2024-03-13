/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class ProyectoMiembro {
    @Column
    private Boolean propietario;
      @ViewReferenced(from = "user", localField = "iduser")
    private UserView userView;
    @Column
    private Boolean active;

    public ProyectoMiembro() {
    }

    public ProyectoMiembro(Boolean propietario, UserView userView, Boolean active) {
        this.propietario = propietario;
        this.userView = userView;
        this.active = active;
    }

    public Boolean getPropietario() {
        return propietario;
    }

    public void setPropietario(Boolean propietario) {
        this.propietario = propietario;
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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProyectoMiembro{");
        sb.append("propietario=").append(propietario);
        sb.append(", userView=").append(userView);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.propietario);
        hash = 53 * hash + Objects.hashCode(this.userView);
        hash = 53 * hash + Objects.hashCode(this.active);
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
        final ProyectoMiembro other = (ProyectoMiembro) obj;
        if (!Objects.equals(this.propietario, other.propietario)) {
            return false;
        }
        if (!Objects.equals(this.userView, other.userView)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }
     
    
    
      
}
