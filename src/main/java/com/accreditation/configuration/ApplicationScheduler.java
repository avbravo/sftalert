/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accreditation.configuration;

import com.accreditation.model.Applicative;
import com.accreditation.model.Comentario;
import com.accreditation.model.Proyecto;
import com.accreditation.model.ProyectoMiembro;
import com.accreditation.model.Tarjeta;
import com.accreditation.model.UserView;
import com.accreditation.repository.ApplicativeRepository;
import com.accreditation.repository.ProyectoRepository;
import com.accreditation.repository.TarjetaRepository;
import com.accreditation.repository.UserViewRepository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import com.sftalert.utils.emails.EmailSender;
import com.sftalert.utils.emails.EmailSenderEvent;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class ApplicationScheduler implements Serializable, JmoordbCoreXHTMLUtil {

    // <editor-fold defaultstate="collapsed" desc="@Config">
    @Inject
    private Config config;
    @Inject
    @ConfigProperty(name = "rowPage", defaultValue = "0")
    private Provider<Integer> rowPage;
    @Inject
    @ConfigProperty(name = "rowPageSmall", defaultValue = "0")
    private Provider<Integer> rowPageSmall;
    @Inject
    @ConfigProperty(name = "idapplicative")
    private Provider<Integer> idapplicative;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="fields()">
    Applicative applicative = new Applicative();
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Event">
    @Inject
    Event<EmailSenderEvent> emailSenderEvent;
    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    Long ejecuciones = 0L;
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
    ApplicativeRepository applicativeRepository;
    @Inject
    ProyectoRepository proyectoRepository;
    @Inject
    TarjetaRepository tarjetaRepository;
    @Inject
    UserViewRepository userViewRepository;
// </editor-fold>

    /**
     * Creates a new instance of ApplicationStart
     */
    public ApplicationScheduler() {
    }

//    @Schedule(second = "15", minute = "48", hour = "21" ,persistent = false)
    @Schedule(second = "*/50", minute = "*/2", hour = "*", persistent = false)
//   @Schedule(minute = "*/2", hour = "*", persistent = false)

    public void verify() {
        try {
            System.out.println("Buscando el aplicative "+idapplicative.get().longValue());
            Optional<Applicative> applicative = applicativeRepository.findByByIdApplicative(idapplicative.get().longValue());
            if (applicative.isPresent()) {
                System.out.println("encontrado");
            } else {
                System.out.println("No encontrado");
            }
            // applicative.get().getEmailconfiguration()
            JmoordbCronometer.startCronometer(MessagesUtil.nameOfClassAndMethod());
            ejecuciones++;
            ConsoleUtil.info("_____________________________________________________________________");

            ConsoleUtil.info("Ejecucion [" + ejecuciones + " ] at" + new Date());
            ConsoleUtil.info("_____________________________________________________________________");

            procesandoUser();

            System.out.println("...........................................................");
            System.out.println(" Voy a detener [" + ejecuciones + "] at" + new Date());
            System.out.println("...........................................................");
            JmoordbCronometer.endCronometer(MessagesUtil.nameOfClassAndMethod(), "\t\t userLogged.getName()");
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error: " + e.getLocalizedMessage());
        }

    }

// <editor-fold defaultstate="collapsed" desc="methods()">
    public Boolean procesandoUser() {
        try {
            System.out.println("=====================================================");
            
            Bson filterActive = eq("active", Boolean.TRUE);

            Bson filter = and(filterActive);
            Document sort = new Document("iduser", -1);
            Integer page = 1;
            Integer size = rowPage.get();

            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalRecords = tarjetaRepository.count(searchCount).intValue();

//            System.out.println("total Registros " + totalRecords);
            Integer totalPage = numberOfPages(totalRecords, rowPage.get());
            if (totalPage.equals(0L)) {
                System.out.println("\t\t\tNo hay usuarios para analizar");
            } else {
                for (int j = 1; j <= totalPage; j++) {
//                    System.out.println(">[ Pagina user ]" + j);
                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);
                    var list = userViewRepository.lookup(search);
                    if (list == null || list.isEmpty()) {
                    } else {
                        for (UserView uv : list) {
                            System.out.println("*****************************************************************");
                            System.out.println("\t\t(iduser) " + uv.getIduser() + " () " + uv.getName());
                            procesandoTarjetas(uv);
                            System.out.println("*****************************************************************");
                        }
                    }

                }
            }
            System.out.println("=====================================================");
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean procesandoTarjetas(Long iduser)  ">

    public Boolean procesandoTarjetas(UserView userView) {
        try {
            Long iduser = userView.getIduser();
            System.out.println("\t\t\t{Procesando tarjeta para iduser [" + iduser + "]}");
            Bson filterUser = eq("user.iduser", iduser);
            Bson filterActive = eq("active", Boolean.TRUE);
            Bson filterColumna = or(eq("columna", "pendiente"), eq("columna", "progreso"));
            Bson filter = and(filterUser, filterActive, filterColumna);
            Document sort = new Document("idtarjeta", -1);
            Integer page = 1;
            Integer size = rowPage.get();

            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalRecords = tarjetaRepository.count(searchCount).intValue();
            System.out.println("\t\t\t{total Registros " + totalRecords + "}");
            Integer totalPage = numberOfPages(totalRecords, rowPage.get());
            if (totalPage.equals(0L)) {
                System.out.println("\t\t\t{tNo hay tarjetas para analizar}");
            } else {
                for (int j = 1; j <= totalPage; j++) {
//                    System.out.println("\t\t\t{[ Pagina ]" + j+"}");
                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);

                    var list = tarjetaRepository.lookup(search);
                    if (list == null || list.isEmpty()) {
//                        System.out.println(" pagina es empty ");
//                        System.out.println("toJson() " + filter.toBsonDocument().toJson());
                    } else {
                        String nameProyecto = "";
                        List<Tarjeta> tarjetaEmails = new ArrayList<>();
                        for (Tarjeta t : list) {

                            Optional<Proyecto> proyecto = proyectoRepository.findByPk(t.getIdproyecto());
                            if (proyecto.isPresent()) {
                                nameProyecto = proyecto.get().getProyecto();
                            } else {
                                System.out.println("No pertenece a ningun proyecto tarjeta" + t.getIdtarjeta());
                            }

                            System.out.println("\t\t\t{ " + t.getIdtarjeta() + " " + t.getTarjeta() + " proyecto" + nameProyecto + "}");
                            System.out.println("\t\t\tDias pendientes " + diasPendientes(t) + " Inicial " + showDate(t.getFechainicial()) + " final " + showDate(t.getFechafinal()));
                           
                              tarjetaEmails.add(t);

                        }
                        System.out.println("voy a enviar el correo ");
//                        if(userView.getIduser().equals(8)){
                            System.out.println(" Enviare correeo");
                            sendEmailTarjeta(tarjetaEmails, userView, "enviando correo");
//                        }
                    }

                }
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }

    // </editor-fold>

    static Integer numberOfPages(Integer rows, Integer rowForPage) {
        Integer numberOfPage = 1;
        try {

            if (rows > 0) {
                numberOfPage = rows / rowForPage;
                if ((rows % rowForPage) > 0) {
                    numberOfPage++;
                }
            }
        } catch (Exception e) {
            System.out.println(
                    "------------------------------------------------------------------------------------------------");
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
            System.out.println("Error " + e.getLocalizedMessage());
            System.out.println(
                    "------------------------------------------------------------------------------------------------");

        }
        return numberOfPage;
    }

    @Override
    public void preDestroy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="void sendEmailTarjeta(Tarjeta tarjeta,String evento)">
    public void sendEmailTarjeta(List<Tarjeta> tarjetaEmails, UserView userView, String evento) {
        try {


            List<String> emailList = new ArrayList<>();

            /**
             *
             */
            Boolean found = Boolean.FALSE;

            if (emailList == null || emailList.isEmpty()) {

            }
            emailList.add(userView.getEmail());

            String tituloEmail = "";
            String mensajeEmail = "";
            if (emailList == null || emailList.isEmpty()) {

                return;
            } else {

                List<String> list = new ArrayList<>();
                emailList.forEach(s -> {
                    if (s == null || s.equals("")) {
                    } else {
                        list.add(s);
                    }

                });

                emailList = list;

                for (Tarjeta t : tarjetaEmails) {
                    Optional<Proyecto> proyecto = proyectoRepository.findByPk(t.getIdproyecto());
                    if (proyecto.isPresent()) {
                        mensajeEmail += "<strong>" + "Proyecto " + ":</strong>" + "  " + proyecto.get().getProyecto() + " <br>"
                                + "Tarjeta" + ": " + t.getTarjeta() + "<br>"
                                + "#" + ": " + t.getIdtarjeta() + "<br>"
                                + "<strong>" + "Descripcion"+ ": " + "</strong>" + t.getDescripcion() + "<br>"
                                + "Fecha de vencimiento" + " <strong>" + showDate(t.getFechainicial())  + "</strong><br><br>"
                                + "Fecha de vencimiento" + " <strong>" + showDate(t.getFechainicial())  + "</strong><br><br>"
                                + "Dias pendientes" + " <strong>" + diasPendientes(t)  + "</strong><br><br>"
                                + "<strong>" + "Visite " + "</strong>" + " <a href=\"" + applicative.getPath() + "\">SFT</a>" + "<br><br>";
                    } else {
                        System.out.println("No pertenece a ningun proyecto tarjeta" + t.getIdtarjeta());
                    }

                }
                if (emailList == null || emailList.isEmpty()) {

                } else {
                    tituloEmail += " " + " Notificaciones de tarjetas";
                    EmailSender emailSender = new EmailSender.Builder()
                            .header(tituloEmail)
                            .messages(mensajeEmail)
                            .pathFile("")
                            .nameFile("")
                            .emailList(emailList)
                            .build();
                    System.out.println("enviando el correo de notificacion");
                    emailSenderEvent.fire(new EmailSenderEvent(emailSender));
                }


            }

        } catch (Exception e) {

            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
}
