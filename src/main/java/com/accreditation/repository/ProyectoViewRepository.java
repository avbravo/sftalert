/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accreditation.repository;

import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.accreditation.model.ProyectoView;
import java.util.List;


/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}", entity = ProyectoView.class,collection = "proyecto")
public interface ProyectoViewRepository extends CrudRepository<ProyectoView, Long>{
    
      @Find
    public List<ProyectoView> findByProyecto(String proyecto);
       @Lookup
public List<ProyectoView> lookup(Search search);
  @Count()
    public Long count(Search... search);
}
