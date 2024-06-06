/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accreditation.schedule;

import com.accreditation.configuration.JmoordbCoreXHTMLUtil;
import com.accreditation.configuration.JmoordbCronometer;
import com.accreditation.model.Applicative;
import com.accreditation.model.Plan;
import com.accreditation.model.Proyecto;
import com.accreditation.model.Sprint;
import com.accreditation.model.Tarjeta;
import com.accreditation.model.UserView;
import com.accreditation.repository.ApplicativeRepository;
import com.accreditation.repository.PlanRepository;
import com.accreditation.repository.ProyectoRepository;
import com.accreditation.repository.ProyectoViewRepository;
import com.accreditation.repository.SprintRepository;
import com.accreditation.repository.UserViewRepository;
import com.jmoordb.core.model.Search;
import com.jmoordb.core.util.ConsoleUtil;
import com.jmoordb.core.util.DocumentUtil;
import com.jmoordb.core.util.MessagesUtil;
import static com.mongodb.client.model.Filters.eq;
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
import org.bson.Document;
import org.bson.conversions.Bson;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Stateless
public class MigradorSprintScheduler implements Serializable, JmoordbCoreXHTMLUtil {

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
    String urlServer = "http://congreso.ls.utp.ac.pa:8085/sft";
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject">
    @Inject
    ApplicativeRepository applicativeRepository;
    @Inject
    ProyectoRepository proyectoRepository;
//    @Inject
//    TarjetaRepository sprintRepository;
    @Inject
    UserViewRepository userViewRepository;
    @Inject
    ProyectoViewRepository proyectoViewRepository;
    
    @Inject
    SprintRepository sprintRepository;
    
    @Inject
    PlanRepository planRepository;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="MigradorSprintScheduler()">
    /**
     * Creates a new instance of ApplicationStart
     */
    public MigradorSprintScheduler() {
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="void schedule()">
    @Schedule(hour = "8", minute = "45", second = "30", persistent = false)

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

//         procesandoUser();
            procesandoSprint();

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

    // <editor-fold defaultstate="collapsed" desc="Boolean procesandoSprint()  ">
    public Boolean procesandoSprint() {
        try {
            System.out.println("__________________________________________________________");
            System.out.println(" (*)                                                    (*)");
            System.out.println("__________________________________________________________");
//            Long iduser = userView.getIduser();

//            Bson filterUser = eq("userView.iduser", iduser);
            Bson filter = eq("active", Boolean.TRUE);
//            Bson filterColumna = or(eq("columna", "pendiente"), eq("columna", "progreso"));
//            Bson filter = and(filterUser, filterActive, filterColumna);
            Document sort = new Document("idsprint", 1);

            Search searchCount = DocumentUtil.convertForLookup(filter, sort, 0, 0);
            Integer totalRecords = sprintRepository.count(searchCount).intValue();
            System.out.println("\t total Records " + totalRecords);
            Integer totalPage = numberOfPages(totalRecords, rowPage.get());
            Integer size = rowPage.get();

            Integer totalTarjetas = 0;
            Integer totalTarjetasNoMigradas = 0;
            if (totalPage.equals(0L)) {
                System.out.println("\t\t\t{No hay sprints para analizar}");
            } else {
                String nameProyecto = "";

                for (int j = 1; j <= totalPage; j++) {
//                    System.out.println("..... procesando " + j + " filter " + filter.toBsonDocument().toJson());
                    Search search = DocumentUtil.convertForLookup(filter, sort, j, size);
                    sprintRepository.setDynamicCollection("");
                    var list = sprintRepository.lookup(search);
                    if (list == null || list.isEmpty()) {
                        System.out.println("..... esta vacia ");
                    } else {

                        for (Sprint s : list) {
                            totalTarjetas++;
                            //sprintEmails.add(t);
                            if (s.getIdsprint() == null) {
                                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println(" \t\t sprint " + s.getIdsprint() + " el proyecto es null");
                                  totalTarjetasNoMigradas++;
                            } else {
                                System.out.println("(* ) Guardando :" + s.getIdsprint() + "(*) Proyecto " + s.getProyectoView().getIdproyecto());
                                Plan plan = new Plan();
                                plan.setActionHistory(s.getActionHistory());
                                plan.setActive(s.getActive());
                                plan.setDescripcion(s.getDescripcion());
                                plan.setEstadisticaCierre(s.getEstadisticaCierre());
                                plan.setEstadisticaCierreColaborador(s.getEstadisticaCierreColaborador());
                                  plan.setFechafinal(s.getFechafinal());
                                  plan.setFechainicial(s.getFechainicial());
                                  plan.setIdsprint(s.getIdsprint());
                                  plan.setOpen(s.getOpen());
                                  plan.setProyectoView(s.getProyectoView());
                                  plan.setSprint(s.getSprint());
                                
                                
                                planRepository.setDynamicCollection("sprint_" +s.getProyectoView().getIdproyecto());
                                if (planRepository.save(plan).isPresent()) {

                                } else {
                                    System.out.println("_______________________________________________________");
                                    System.out.println("(*) No se guardo " + s.getIdsprint());
                                    totalTarjetasNoMigradas++;
                                    System.out.println("_______________________________________________________");
                                }
                            }

                        }

                    }

                }

                System.out.println("\t________________________________________________________");
                System.out.println("\t <>Total de Tarjetas: " + totalTarjetas);
                System.out.println("\t <>Total de Tarjetas No migradas: " + totalTarjetasNoMigradas);
                System.out.println("\t________________________________________________________");

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
    // <editor-fold defaultstate="collapsed" desc="void sendEmailTarjeta(Tarjeta sprint,String evento)">
    public void sendEmailTarjeta(List<Tarjeta> sprintEmails, UserView userView, String evento) {
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
                for (Tarjeta t : sprintEmails) {

                    backlogMessage = "<br>";
                    nameOfProject = "";
                    if (t.getBacklog() && t.getIdsprint().equals(0L)) {
                        backlogMessage = "<strong> Esta sprint se encuentra en la reserva</strong><br><br>";
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
                            + "<strong>#" + ":</strong> " + t.getIdsprint() + "<br>"
                            + "<strong>" + "Descripcion" + ": " + "</strong>" + t.getDescripcion() + "<br>"
                            + "<strong>" + "Columna" + ": " + "</strong>" + t.getColumna() + "<br>"
                            + "<strong>Fecha inicial" + " </strong>" + showDate(t.getFechainicial()) + "    "
                            + "<strong>Fecha final" + " </strong>" + showDate(t.getFechafinal()) + "><br>"
                            + "<strong>Dias pendientes:" + " </strong>" + diasPendientes(t) + "<br>"
                            + "<strong>Comentarios:" + " </strong>" + t.getComentario().size() + " "
                            + "<strong>Tareas:" + " </strong>" + t.getTarea().size() + " "
                            + "<stroMejorar el procedimiento de ordenación de sprints en base a la ultima modificacion realizada.ng>Impedimentos:" + " </strong>" + t.getImpedimento().size() + " "
                            + "<strong>Archivos:" + " </strong>" + t.getArchivo().size() + " "
                            + "<strong>Etiquetas:" + " </strong>" + t.getEtiqueta().size() + "<br>"
                            + backlogMessage
                            + "<hr>";

                }
                if (emailList == null || emailList.isEmpty()) {

                } else {

                    mensajeEmail += "<strong>" + "Visite " + "</strong>" + " <a href=\"" + urlServer + "\">SFT</a>" + "<br><br>";
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
