/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.repository;

import com.jmoordb.core.annotation.enumerations.CaseSensitive;
import com.jmoordb.core.annotation.enumerations.LikeByType;
import com.jmoordb.core.annotation.enumerations.TypeOrder;
import com.jmoordb.core.annotation.repository.Count;
import com.jmoordb.core.annotation.repository.CountLikeBy;
import com.jmoordb.core.annotation.repository.Find;
import com.jmoordb.core.annotation.repository.LikeBy;
import com.jmoordb.core.annotation.repository.Lookup;
import com.jmoordb.core.annotation.repository.Repository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.repository.CrudRepository;
import com.accreditation.model.Tipotarjeta;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}", entity = Tipotarjeta.class)
public interface TipotarjetaRepository extends CrudRepository<Tipotarjeta, Long> {

    @Find
    public Optional<Tipotarjeta> findByTipotarjeta(String tipotarjeta);

    @Lookup
    public List<Tipotarjeta> lookup(Search search);

    @Count()
    public Long count(Search... search);
    
    @CountLikeBy(caseSensitive = CaseSensitive.NO, likeByType = LikeByType.ANYWHERE)
    public Long countLikeByTipotarjeta(String tipotarjeta);
    
    
    @LikeBy(caseSensitive = CaseSensitive.NO, typeOrder = TypeOrder.ASC, likeByType = LikeByType.ANYWHERE)
    public List<Tipotarjeta> likeByTipotarjeta(String tipotarjeta);
    
   


}
