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
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Stateless
public class ApplicationScheduler implements Serializable {

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
    public ApplicationScheduler() {
    }

    @Schedule(second = "15", minute = "48", hour = "21" ,persistent = false)
//    @Schedule(second = "*/50", minute = "*/2", hour = "*", persistent = false)
//    @Schedule(minute = "*/2", hour = "*", persistent = false)

    public void verify() {
        try {
            
            ejecuciones++;
            ConsoleUtil.info("_____________________________________________________________________");

            ConsoleUtil.info("Ejecucion [" + ejecuciones + " ] at" + new Date());
            ConsoleUtil.info("_____________________________________________________________________");
            procesandoTarjetas(0);
//conFuture();
//  try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//    Future<String> future = executor.submit(() -> "Hello World");
//    System.out.println(future.get());
//    System.out.println("The end!");
//}
//            try (var service = Executors.newVirtualThreadPerTaskExecutor()) {
//                service.submit(() -> procesandoTarjetas(1));
//                service.submit(() -> procesandoTarjetas(2));
//            }
//            System.out.println("******************************************************");;
//            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
//
//                IntStream.range(0, 10_000).forEach(i -> {
//
//                    executor.submit(() -> {
//                        System.out.println("\tEjecutando la tarea " + i);
//                         Thread.sleep(Duration.ofSeconds(1));
//                        procesandoTarjetas(i);
//                        return i;
//
//                    });
//
//                });
//
//              //   Wait for all tasks to complete.
//            } // The executor is automatically closed, and it waits for task completion.
System.out.println("...........................................................");
 System.out.println(" Voy a detener ["+ejecuciones+"] at" + new Date());
 System.out.println("...........................................................");
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }

    }

    public  Boolean procesandoTarjetas(Integer i) {
        try {
            System.out.println("\t\tProcesando tarjeta [" + i + "]");
            Bson filter = eq("active", Boolean.TRUE);
            Document sort = new Document("idtarjeta", -1);
            Integer page = 1;
            Integer size = rowPage.get();

            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalPage = tarjetaRepository.count(searchCount).intValue();

            if (totalPage.equals(0L)) {
                System.out.println("\t\t\tNo hay tarjetas para analizar");
            } else {
                for (int j = 1; j <= totalPage; j++) {

                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);

                    var list = tarjetaRepository.lookup(search);
                    if (list == null || list.isEmpty()) {

                    } else {
                        for (Tarjeta t : list) {
                            System.out.println("\t\t\t\t---> " + t.getIdtarjeta() + " " + t.getTarjeta());
                        }
                    }

                }
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }

    public void conFuture() throws ExecutionException {
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            IntStream.range(0, 10_000).forEach(i -> {
                Future<String> future = executor.submit(() -> {
                    return blockingOperation(i);
                });
                String result;
                try {
                    result = future.get();
                    System.out.println("Result: " + result);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ApplicationScheduler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(ApplicationScheduler.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

            executor.shutdown();
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
    }

    private String blockingOperation(Integer i) {
        try {
            System.out.println(">>>>>>>> blockingOperation "+i);
             procesandoTarjetas(i);
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
       
//            Thread.sleep(2000);
        return "Blocking operation completed "+i;
    }

}
