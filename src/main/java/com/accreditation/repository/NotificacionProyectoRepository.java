/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accreditation.repository;

import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.accreditation.model.NotificacionProyecto;
import com.accreditation.model.Proyecto;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}",entity = NotificacionProyecto.class)
public interface NotificacionProyectoRepository extends CrudRepository<NotificacionProyecto, Long> {
    
      @Lookup
    public List<NotificacionProyecto> lookup(Search search);

    @Count()
    public Long count(Search... search);
}
