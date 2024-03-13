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
import com.accreditation.model.Otp;
import com.accreditation.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
@Repository(entity = Otp.class)
public interface OtpRepository extends CrudRepository<Otp, Long> {

    @Find
    public Optional<Otp> findByIdotp(Long idotp);
    @Find
    public List<Otp> findByIduser(Long iduser);
    
    @Find
    public List<Otp> findByOtp(String otp);
    @Find
    public List<Otp> findByEmail(String email);
    
    @Lookup
    public List<Otp> lookup(Search search);

    @Count()
    public Long count(Search... search);
}
