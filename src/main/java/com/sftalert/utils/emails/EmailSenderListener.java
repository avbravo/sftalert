/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.emails;
// <editor-fold defaultstate="collapsed" desc="import">

import com.accreditation.model.Applicative;
import com.accreditation.model.Emailconfiguration;
import com.accreditation.repository.ApplicativeRepository;
import com.accreditation.repository.ProyectoRepository;
import com.accreditation.repository.TarjetaRepository;
import com.accreditation.repository.UserViewRepository;
import com.jmoordb.core.util.MessagesUtil;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
// </editor-fold>

/**
 *
 * @author avbravo
 */
@Stateless
public class EmailSenderListener {
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
    // <editor-fold defaultstate="collapsed" desc="field">
    private Applicative applicative = new Applicative();

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject)">
    @Inject
    EmailServices emailServices;
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" init()">

    @PostConstruct
    public void init() {
        try {
            Optional<Applicative> applicativeOptional = applicativeRepository.findByPk(idapplicative.get().longValue());
            if (applicativeOptional.isPresent()) {
                applicative = applicativeOptional.get();
            } else {

            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="void listenerEvent(@Observes EmailSenderEvent emailSenderevt)">
    public void listenerEvent(@Observes EmailSenderEvent emailSenderevt) {
        try {

            EmailSender emailSender = emailSenderevt.getEmailSender();

            List<Emailconfiguration> emailconfigurationList = applicative.getEmailconfiguration();

            if (emailconfigurationList == null || emailconfigurationList.isEmpty()) {
                System.out.println(" No hay registros de configuración de email");

                return;
            }

            Optional<Emailconfiguration> emailconfigurationOptional = emailconfigurationList.stream().filter(x -> x.getActive().equals(Boolean.TRUE)).findFirst();

            if (!emailconfigurationOptional.isPresent()) {

                System.out.println(" No hay registros de configuración de email");
                return;
            }

            if (emailSender == null) {
                System.out.println("  Email sender es nulo");

                return;
            }

            /**
             * Agrega el de configuracion como retimente
             */
//                     
//            var newEmail = emailconfigurationOptional.get().getEmail();
//
//
//            List<String> list = new ArrayList<>();
//            for (String s : emailSender.getEmailList()) {
//
//                list.add(s);
//            }
//
//            list.add(newEmail);
//
//            emailSender.setEmailList(list);
            EmailRecipients emailRecipients = EmailUtils.divideDestinatary(emailSender.getEmailList());

            if (emailSender.getPathFile() == null || emailSender.getPathFile().equals("")) {

                Future<String> completableFutureCC = emailServices.sendEmailCccBccAsync(
                        emailRecipients.getTo(),
                        emailRecipients.getCc(),
                        emailRecipients.getBcc(),
                        emailSender.getHeader(),
                        emailSender.getMessages()
                );

            } else {

                Future<String> completableFutureCC = emailServices.sendEmailCccBccWithFileAsync(
                        emailRecipients.getTo(),
                        emailRecipients.getCc(),
                        emailRecipients.getBcc(),
                        emailSender.getHeader(),
                        emailSender.getMessages(),
                        emailSender.getPathFile(),
                        emailSender.getNameFile()
                );

            }

        } catch (Exception e) {

            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());

        }

    }
    // </editor-fold>

}
