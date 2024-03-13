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
import com.accreditation.model.ApplicativeView;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = ApplicativeView.class,collection = "applicative")
public interface ApplicativeViewRepository extends CrudRepository<ApplicativeView, Long> {
    @Find
    public Optional<ApplicativeView> findByApplicative(String central);
    
    @Lookup
public List<ApplicativeView> lookup(Search search);
  @Count()
    public Long count(Search... search);
}
