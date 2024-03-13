/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.pojos;

import java.util.Date;

/**
 *
 * @author avbravo
 */
public class Userlogutils {
    private String username;
    private Date datetime;
    private String description;
    
    

    public Userlogutils() {
    }

    public Userlogutils(String username, Date datetime, String description) {
        this.username = username;
        this.datetime = datetime;
        this.description = description;
    }

    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
