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
import com.accreditation.model.Organigram;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(database = "{mongodb.database1}", entity = Organigram.class)
public interface OrganigramRepository extends CrudRepository<Organigram, Long> {

    @Find
    public Optional<Organigram> findByIdorganigram(Long idorganigram);
    
    @Lookup
    public List<Organigram> lookup(Search search);

    @Count()
    public Long count(Search... search);
}
