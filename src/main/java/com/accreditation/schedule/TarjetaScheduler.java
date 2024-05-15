/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accreditation.schedule;

import com.accreditation.configuration.JmoordbCoreXHTMLUtil;
import com.accreditation.configuration.JmoordbCronometer;
import com.accreditation.model.Applicative;
import com.accreditation.model.Proyecto;
import com.accreditation.model.Tarjeta;
import com.accreditation.model.UserView;
import com.accreditation.repository.ApplicativeRepository;
import com.accreditation.repository.ProyectoRepository;
import com.accreditation.repository.TarjetaRepository;
import com.accreditation.repository.UserViewRepository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.JmoordbCoreDateUtil;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Stateless
public class TarjetaScheduler implements Serializable, JmoordbCoreXHTMLUtil {

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
    List<Proyecto> proyectos = new ArrayList<>();
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="@Event">
    @Inject
    Event<EmailSenderEvent> emailSenderEvent;
    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    Long ejecuciones = 0L;
    Integer milisegundosPausa = 1500;
    String urlServer="http://congreso.ls.utp.ac.pa:8085/sft";
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

    // <editor-fold defaultstate="collapsed" desc="TarjetaScheduler()">
    /**
     * Creates a new instance of ApplicationStart
     */
    public TarjetaScheduler() {
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="void schedule()">
//    @Schedule(second = "30", minute = "50", hour = "9", persistent = false)
    @Schedule(second = "30", minute = "24", hour = "10", persistent = false)

    public void schedule() {
        try {
//            System.out.println("Buscando el aplicative " + idapplicative.get().longValue());
            Optional<Applicative> applicativeOptional = applicativeRepository.findByByIdApplicative(idapplicative.get().longValue());
            if (applicativeOptional.isPresent()) {
//                System.out.println("encontrado");
applicative = applicativeOptional.get();
        
            } else {
                
                System.out.println("No encontro applicative");
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

            proyectos = new ArrayList<>();
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error: " + e.getLocalizedMessage());
        }

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Boolean procesandoUser()">
    public Boolean procesandoUser() {
        try {
            System.out.println("=====================================================");
            Bson filterActive = eq("active", Boolean.TRUE);
            Bson filter = and(filterActive);
            Document sort = new Document("iduser", -1);
            Integer page = 1;
            Integer size = rowPage.get();
            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalRecords = userViewRepository.count(searchCount).intValue();
            Integer totalPage = numberOfPages(totalRecords, rowPage.get());
            if (totalPage.equals(0L)) {
                System.out.println("\t\t\tNo hay usuarios para analizar");
            } else {
                for (int j = 1; j <= totalPage; j++) {
                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);
                    var list = userViewRepository.lookup(search);
                    if (list == null || list.isEmpty()) {
                    } else {
                        for (UserView uv : list) {

                            if (uv.getRecibirNotificacion()) {
                                System.out.println("*****************************************************************");
                                System.out.println("\t\t  (iduser) " + uv.getIduser() + " () " + uv.getName() + " " + uv.getEmail());
                                procesandoTarjetas(uv);

                    
                                System.out.println("*****************************************************************");
//                            }

                            }

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

            Bson filterUser = eq("user.iduser", iduser);
            Bson filterActive = eq("active", Boolean.TRUE);
            Bson filterColumna = or(eq("columna", "pendiente"), eq("columna", "progreso"));
            Bson filter = and(filterUser, filterActive, filterColumna);
            Document sort = new Document("idtarjeta", -1);
            Integer page = 1;
            Integer size = rowPage.get();

            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalRecords = tarjetaRepository.count(searchCount).intValue();

            Integer totalPage = numberOfPages(totalRecords, rowPage.get());
            if (totalPage.equals(0L)) {
                System.out.println("\t\t\t{tNo hay tarjetas para analizar}");
            } else {
                String nameProyecto = "";
                List<Tarjeta> tarjetaEmails = new ArrayList<>();
                for (int j = 1; j <= totalPage; j++) {

                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);

                    var list = tarjetaRepository.lookup(search);
                    if (list == null || list.isEmpty()) {
                    } else {

                        for (Tarjeta t : list) {
                            tarjetaEmails.add(t);

                        }

                    }

                }
                if (tarjetaEmails == null || tarjetaEmails.isEmpty()) {

                } else {
                    System.out.println(" Intendando enviar correo a " + userView.getName());
                    tarjetaEmails = tarjetaEmails.stream().sorted(Comparator.comparing(Tarjeta::getIdtarjeta).reversed()).collect(Collectors.toList());
                    sendEmailTarjeta(tarjetaEmails, userView, "enviando correo");
                    /**
                     * Pausa unos milesegundos para dar tiempo al cierre de la conexión al servidor de correo
                     */
                    Thread.sleep(milisegundosPausa);
                    System.out.println(" (ok)  a " + userView.getName());
                }

            }
        } catch (Exception e) {
            System.out.println("Error " + e.getLocalizedMessage());
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Integer numberOfPages(Integer rows, Integer rowForPage)">
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
            System.out.println("------------------------------------------------------------------------------------------------");
        }
        return numberOfPage;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="void preDestroy()">

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
                String backlogMessage = "<br>";
                String nameOfProject = "";
                for (Tarjeta t : tarjetaEmails) {

                    backlogMessage = "<br>";
                    nameOfProject = "";
                    if (t.getBacklog() && t.getIdsprint().equals(0L)) {
                        backlogMessage = "<strong> Esta tarjeta se encuentra en la reserva</strong><br><br>";
                    }

                    if (proyectos == null || proyectos.isEmpty()) {
                        Optional<Proyecto> proyecto = proyectoRepository.findByPk(t.getIdproyecto());
                        if (proyecto.isPresent()) {
                            nameOfProject = proyecto.get().getProyecto();
                            proyectos.add(proyecto.get());
                        }
                    } else {
                        for (Proyecto p : proyectos) {
                            if (p.getIdproyecto().equals(t.getIdproyecto())) {
                                nameOfProject = p.getProyecto();
                            }
                        }
                        if (nameOfProject.isEmpty() || nameOfProject.equals("")) {
                            Optional<Proyecto> proyecto = proyectoRepository.findByPk(t.getIdproyecto());
                            if (proyecto.isPresent()) {
                                nameOfProject = proyecto.get().getProyecto();
                                proyectos.add(proyecto.get());
                            }
                        }

                    }

                    mensajeEmail += "<strong>" + "Proyecto " + ":</strong>" + "  " + nameOfProject + " <br>"
                            + "<strong>Tarjeta" + ": </strong>" + t.getTarjeta() + "<br>"
                            + "<strong>#" + ":</strong> " + t.getIdtarjeta() + "<br>"
                            + "<strong>" + "Descripcion" + ": " + "</strong>" + t.getDescripcion() + "<br>"
                            + "<strong>" + "Columna" + ": " + "</strong>" + t.getColumna() + "<br>"
                            + "<strong>Fecha inicial" + " </strong>" + showDate(t.getFechainicial()) + "    "
                            + "<strong>Fecha final" + " </strong>" + showDate(t.getFechafinal()) + "><br>"
                            + "<strong>Dias pendientes:" + " </strong>" + diasPendientes(t) + "<br>"
                            + "<strong>Comentarios:" + " </strong>" + t.getComentario().size() + " "
                            + "<strong>Tareas:" + " </strong>" + t.getTarea().size() + " "
                            + "<stroMejorar el procedimiento de ordenación de tarjetas en base a la ultima modificacion realizada.ng>Impedimentos:" + " </strong>" + t.getImpedimento().size() + " "
                            + "<strong>Archivos:" + " </strong>" + t.getArchivo().size() + " "
                            + "<strong>Etiquetas:" + " </strong>" + t.getEtiqueta().size() + "<br>"
                            + backlogMessage
                            + "<hr>";

                }
                if (emailList == null || emailList.isEmpty()) {

                } else {
                
                    mensajeEmail += "<strong>" + "Visite " + "</strong>" + " <a href=\""+urlServer  + "\">SFT</a>" + "<br><br>";
                    tituloEmail += " " + " Resumen de Tarjetas pendiente y en progreso SFT";
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
