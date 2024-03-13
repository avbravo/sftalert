/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accreditation.configuration;

import com.accreditation.model.Tarjeta;
import com.accreditation.repository.TarjetaRepository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import static com.mongodb.client.model.Filters.eq;
import jakarta.annotation.Resource;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Timeout;

/**
 *
 * @author avbravo
 */
@Stateless
public class MyTimer implements Serializable {
    
        @Resource
    private SessionContext context;
          private Logger logger = Logger.getLogger(MyTimer.class.getName());
@Inject
    private Config config;
     @Inject
    @ConfigProperty(name = "rowPage", defaultValue = "0")
    private Provider<Integer> rowPage;
      @Inject
    @ConfigProperty(name = "rowPageSmall", defaultValue = "0")
   private Provider<Integer> rowPageSmall;
    
    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    Long ejecuciones = 0L;
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="methods()">
    @Inject
    TarjetaRepository tarjetaRepository;
// </editor-fold>

    /**
     * Creates a new instance of ApplicationStart
     */
    public MyTimer() {
    }




    public void initTimer(String message){
        context.getTimerService().createTimer(1000, message);
    }

    @Timeout
    public void execute(){
        logger.info("Starting");
        System.out.println(" []###################################################Ejecutando .............");
        context.getTimerService().getAllTimers().stream().forEach(
                timer -> logger.info(String.valueOf(timer.getInfo()))
        
        );
        

        logger.info("Ending");
    }    

    
    public void saludo(){
        System.out.println("[]saludo [] "); 
    }
    public void verify() {
        try {
            ejecuciones++;
            ConsoleUtil.info("_____________________________________________________________________");

            ConsoleUtil.info("Ejecucion [" + ejecuciones + "]");
            ConsoleUtil.info("Lanzado cada" + MessagesUtil.nameOfClassAndMethod() + " at " + new Date());
            ConsoleUtil.info("_____________________________________________________________________");

//               Search search = new Search();
            Bson filter = eq("active", Boolean.TRUE);
            Document sort = new Document("idtarjeta", -1);
            Integer page = 1;
            Integer size = rowPage.get();

            
            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
Integer totalPage =tarjetaRepository.count(searchCount).intValue();






if(totalPage.equals(0L)){
    System.out.println("No hay tarjetas para analizar");
}else{
    for (int i = 1; i <= totalPage; i++) {
       
Search search = DocumentUtil.convertForLookup(filter, sort, i, size);
               
         var list = tarjetaRepository.lookup(search);
            if (list == null || list.isEmpty()) {

            } else {
                for (Tarjeta t : list) {
                    System.out.println("---> " + t.getIdtarjeta() + " " + t.getTarjeta());
                }
            }

    }
}
           
            // Boletas boletas = (Boletas) JmoordbContext.get("jmoordb_boletas");
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

    }

}
