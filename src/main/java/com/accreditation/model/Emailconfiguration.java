/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.model;

import com.jmoordb.core.annotation.Column;
import com.jmoordb.core.annotation.DocumentEmbeddable;
import com.jmoordb.core.annotation.Ignore;
import java.util.Objects;

/**
 *
 * @author avbravo
 */
@DocumentEmbeddable
public class Emailconfiguration {
    @Ignore
    private Long id;
     @Column
      private String email;
     @Column
    private String password;
     @Column
    private String mailSmtpHost;
     @Column
    private String mailSmtpAuth;
     @Column
    private String mailSmtpPort;
     @Column
    private String mailSmtpStarttlsEnable;
 @Column
    private Boolean active;
    public Emailconfiguration() {
    }

    public Emailconfiguration(Long id, String email, String password, String mailSmtpHost, String mailSmtpAuth, String mailSmtpPort, String mailSmtpStarttlsEnable, Boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mailSmtpHost = mailSmtpHost;
        this.mailSmtpAuth = mailSmtpAuth;
        this.mailSmtpPort = mailSmtpPort;
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public void setMailSmtpAuth(String mailSmtpAuth) {
        this.mailSmtpAuth = mailSmtpAuth;
    }

    public String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }

    public String getMailSmtpStarttlsEnable() {
        return mailSmtpStarttlsEnable;
    }

    public void setMailSmtpStarttlsEnable(String mailSmtpStarttlsEnable) {
        this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
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
        sb.append("Emailconfiguration{");
        sb.append("id=").append(id);
        sb.append(", email=").append(email);
        sb.append(", password=").append(password);
        sb.append(", mailSmtpHost=").append(mailSmtpHost);
        sb.append(", mailSmtpAuth=").append(mailSmtpAuth);
        sb.append(", mailSmtpPort=").append(mailSmtpPort);
        sb.append(", mailSmtpStarttlsEnable=").append(mailSmtpStarttlsEnable);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.password);
        hash = 89 * hash + Objects.hashCode(this.mailSmtpHost);
        hash = 89 * hash + Objects.hashCode(this.mailSmtpAuth);
        hash = 89 * hash + Objects.hashCode(this.mailSmtpPort);
        hash = 89 * hash + Objects.hashCode(this.mailSmtpStarttlsEnable);
        hash = 89 * hash + Objects.hashCode(this.active);
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
        final Emailconfiguration other = (Emailconfiguration) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.mailSmtpHost, other.mailSmtpHost)) {
            return false;
        }
        if (!Objects.equals(this.mailSmtpAuth, other.mailSmtpAuth)) {
            return false;
        }
        if (!Objects.equals(this.mailSmtpPort, other.mailSmtpPort)) {
            return false;
        }
        if (!Objects.equals(this.mailSmtpStarttlsEnable, other.mailSmtpStarttlsEnable)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    

    

      
    
   
}
