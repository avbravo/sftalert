/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import com.jmoordb.core.annotation.Referenced;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Profile {

    @Ignore
    private Long id;

    @ViewReferenced(from = "applicative", localField = "idapplicative")
    private ApplicativeView applicativeView;
    @Referenced(from = "role", localField = "idrole")
    private Role role;
    @ViewReferenced(from = "departament", localField = "iddepartament")
    private DepartamentView departamentView;

    @Column
    private Boolean active;

    public Profile() {
    }

    public Profile(Long id, ApplicativeView applicativeView, Role role, DepartamentView departamentView, Boolean active) {
        this.id = id;
        this.applicativeView = applicativeView;
        this.role = role;
        this.departamentView = departamentView;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicativeView getApplicativeView() {
        return applicativeView;
    }

    public void setApplicativeView(ApplicativeView applicativeView) {
        this.applicativeView = applicativeView;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DepartamentView getDepartamentView() {
        return departamentView;
    }

    public void setDepartamentView(DepartamentView departamentView) {
        this.departamentView = departamentView;
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
        sb.append("Profile{");
        sb.append("id=").append(id);
        sb.append(", applicativeView=").append(applicativeView);
        sb.append(", role=").append(role);
        sb.append(", departamentView=").append(departamentView);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.applicativeView);
        hash = 53 * hash + Objects.hashCode(this.role);
        hash = 53 * hash + Objects.hashCode(this.departamentView);
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
        final Profile other = (Profile) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.applicativeView, other.applicativeView)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.departamentView, other.departamentView)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    
    
}
