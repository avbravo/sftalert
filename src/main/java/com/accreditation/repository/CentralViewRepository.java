/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accreditation.repository;

import com.jmoordb.core.annotation.enumerations.CaseSensitive;
import com.jmoordb.core.annotation.enumerations.TypeOrder;
import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.LikeBy;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.accreditation.model.CentralView;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = CentralView.class, collection = "central")
public interface CentralViewRepository extends CrudRepository<CentralView, Long> {

    @Find
    public Optional<CentralView> findByCentral(String central);

    @Find
    public Optional<CentralView> findByIdcentral(Long idcentral);

    @Lookup
    public List<CentralView> lookup(Search search);

    @Count()
    public Long count(Search... search);

    @LikeBy(caseSensitive = CaseSensitive.NO, typeOrder = TypeOrder.ASC)
    public List<CentralView> likeByCentral(String central);
}
