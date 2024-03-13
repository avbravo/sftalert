/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import com.jmoordb.core.annotation.ViewReferenced;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class OrganigramDepartament {
   @Ignore
   private Long id;

    @ViewReferenced(from = "departament", localField = "iddepartament")
    private DepartamentView departamentView;

    public OrganigramDepartament() {
    }

    
    
    
    public OrganigramDepartament(Long id, DepartamentView departamentView) {
        this.id = id;
        this.departamentView = departamentView;
    }

    public DepartamentView getDepartamentView() {
        return departamentView;
    }

    public void setDepartamentView(DepartamentView departamentView) {
        this.departamentView = departamentView;
    }
   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.departamentView);
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
        final OrganigramDepartament other = (OrganigramDepartament) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.departamentView, other.departamentView);
    }

    @Override
    public String toString() {
        return "OrganigramDepartament{" + "id=" + id + ", departamentView=" + departamentView + '}';
    }

   
   
   
}
