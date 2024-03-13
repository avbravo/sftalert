/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.configuration;

import com.jmoordb.core.util.MessagesUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Shutdown;
import java.util.Date;
import org.eclipse.microprofile.health.Startup;

/**
 *
 * @author avbravo
 */
@ApplicationScoped
public class StartupObserver {


    public void observeStartup(@Observes Startup startupEvent) {

        try {
            System.out.println("Iniciando aplicative...... at "+new Date());
        } catch (Exception e) {
             MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

    }
//
    public void observeShutdown(@Observes Shutdown shutdownEvent) {
        System.out.println("CDI Container is stopping");
    }
}
