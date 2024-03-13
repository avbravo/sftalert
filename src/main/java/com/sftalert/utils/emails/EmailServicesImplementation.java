/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sftalert.utils.emails;

import com.accreditation.model.Applicative;
import com.accreditation.model.Emailconfiguration;
import com.accreditation.repository.ApplicativeRepository;
import com.jmoordb.core.util.MessagesUtil;

import com.jmoordbcoreencripter.jmoordbencripter.Encryptor;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Provider;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 *
 * @author avbravo
 */
@Named
@ApplicationScoped
public class EmailServicesImplementation implements EmailServices {
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
    // <editor-fold defaultstate="collapsed" desc="@Repository">
    @Inject
            ApplicativeRepository applicativeRepository;
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="field">

    private Applicative applicative = new Applicative();

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="SecretKey()">
    private String secretKey = "SCox1jmWrkma$*opne2Amwz";
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="@Inject)">
    
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

    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmail(String emailDestinatario,String titulo, String mensaje)">
    @Override
    public Boolean sendEmail(String emailDestinatario, String titulo, String mensaje) {
        Boolean result = Boolean.FALSE;
        try {

            List<Emailconfiguration> emailconfigurationList = applicative.getEmailconfiguration();
            if (emailconfigurationList == null || emailconfigurationList.isEmpty()) {
                System.out.println("No hay registros de configuracion de email");
                
                return false;
            }

            Optional<Emailconfiguration> emailconfigurationOptional = emailconfigurationList.stream().filter(x -> x.getActive().equals(Boolean.TRUE)).findFirst();
            if (!emailconfigurationOptional.isPresent()) {
                   System.out.println("No hay registros de configuracion de email activos");
                return false;
            }
            emailconfigurationOptional.get();

            String to = emailDestinatario;
            String from = emailconfigurationOptional.get().getEmail();
            final String username = emailconfigurationOptional.get().getEmail();
            final String password = Encryptor.decrypt(emailconfigurationOptional.get().getPassword(), secretKey,MessagesUtil.nameOfClassAndMethod() );
            String host = emailconfigurationOptional.get().getMailSmtpHost();
            String mailSmtpAuth = emailconfigurationOptional.get().getMailSmtpAuth();
            String mailSmtpStarttlsEnable = emailconfigurationOptional.get().getMailSmtpStarttlsEnable();
            String mailSmtpPort = emailconfigurationOptional.get().getMailSmtpPort();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailSmtpAuth);
            props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", mailSmtpPort);

            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(props, authenticator);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject(titulo);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);

            result = Boolean.TRUE;
        } catch (Exception e) {
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return result;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmail(String titulo, String mensaje, List<String> emailList)">
    @Override
    public Boolean sendEmail(String titulo, String mensaje, List<String> emailList) {
        Boolean result = Boolean.FALSE;
        try {

            List<Emailconfiguration> emailconfigurationList = applicative.getEmailconfiguration();
            if (emailconfigurationList == null || emailconfigurationList.isEmpty()) {
                System.out.println("No hay registros de configuración de email");
                return false;
            }

            Optional<Emailconfiguration> emailconfigurationOptional = emailconfigurationList.stream().filter(x -> x.getActive().equals(Boolean.TRUE)).findFirst();
            if (!emailconfigurationOptional.isPresent()) {
                 System.out.println("No hay registros de configuración de email activos");
                return false;
            }
            emailconfigurationOptional.get();

            EmailRecipients emailRecipients = EmailUtils.divideDestinatary(emailList);

            InternetAddress[] toAddress = new InternetAddress[emailRecipients.getTo().length];
            // To get the array of toaddresses
            for (int i = 0; i < emailRecipients.getTo().length; i++) {
                toAddress[i] = new InternetAddress(emailRecipients.getTo()[i]);
            }

            InternetAddress[] bccAddress = new InternetAddress[emailRecipients.getBcc().length];

            // To get the array of bccaddresses
            for (int i = 0; i < emailRecipients.getBcc().length; i++) {
                bccAddress[i] = new InternetAddress(emailRecipients.getBcc()[i]);
            }

            InternetAddress[] ccAddress = new InternetAddress[emailRecipients.getCc().length];

            // To get the array of ccaddresses
            for (int i = 0; i < emailRecipients.getCc().length; i++) {
                ccAddress[i] = new InternetAddress(emailRecipients.getCc()[i]);
            }

            String from = emailconfigurationOptional.get().getEmail();

            final String username = emailconfigurationOptional.get().getEmail();

            final String password = Encryptor.decrypt(emailconfigurationOptional.get().getPassword(), secretKey,MessagesUtil.nameOfClassAndMethod());

            String host = emailconfigurationOptional.get().getMailSmtpHost();

            String mailSmtpAuth = emailconfigurationOptional.get().getMailSmtpAuth();
            String mailSmtpStarttlsEnable = emailconfigurationOptional.get().getMailSmtpStarttlsEnable();
            String mailSmtpPort = emailconfigurationOptional.get().getMailSmtpPort();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailSmtpAuth);
            props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", mailSmtpPort);

            //create the Session object
            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(props, authenticator);

            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            // Set cc: header field of the header.
            for (int i = 0; i < ccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
            }
            // Set bcc: header field of the header.
            for (int i = 0; i < bccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }

            //set email subject field
            message.setSubject(titulo);
            //set the content of the email message
            message.setContent(mensaje, "text/html; charset=utf-8");
            //send the email message
            Transport.send(message);

            result = Boolean.TRUE;
        } catch (Exception e) {
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return result;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmail(String[] to, String[] cc, String[] bcc, String titulo, String mensaje)">
    @Override
    public Boolean sendEmail(String[] to, String[] cc, String[] bcc, String titulo, String mensaje) {
        Boolean result = Boolean.FALSE;
        try {

            List<Emailconfiguration> emailconfigurationList = applicative.getEmailconfiguration();
            if (emailconfigurationList == null || emailconfigurationList.isEmpty()) {
     System.out.println("No hay configuracion de emails");
                return false;
            }

            Optional<Emailconfiguration> emailconfigurationOptional = emailconfigurationList.stream().filter(x -> x.getActive().equals(Boolean.TRUE)).findFirst();
            if (!emailconfigurationOptional.isPresent()) {
              System.out.println("No hay configuracion de emails activo");
                return false;
            }
            emailconfigurationOptional.get();

//cc, bcc, to multiples
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of toaddresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            InternetAddress[] bccAddress = new InternetAddress[bcc.length];

            // To get the array of bccaddresses
            for (int i = 0; i < bcc.length; i++) {
                bccAddress[i] = new InternetAddress(bcc[i]);
            }

            InternetAddress[] ccAddress = new InternetAddress[cc.length];

            // To get the array of ccaddresses
            for (int i = 0; i < cc.length; i++) {
                ccAddress[i] = new InternetAddress(cc[i]);
            }

            String from = emailconfigurationOptional.get().getEmail();
//            //provide Mailtrap's username
            final String username = emailconfigurationOptional.get().getEmail();
//            //provide Mailtrap's password
            final String password = Encryptor.decrypt(emailconfigurationOptional.get().getPassword(), secretKey,MessagesUtil.nameOfClassAndMethod());
//            //provide Mailtrap's host address
            String host = emailconfigurationOptional.get().getMailSmtpHost();
//            //configure Mailtrap's SMTP server details

            String mailSmtpAuth = emailconfigurationOptional.get().getMailSmtpAuth();
            String mailSmtpStarttlsEnable = emailconfigurationOptional.get().getMailSmtpStarttlsEnable();
            String mailSmtpPort = emailconfigurationOptional.get().getMailSmtpPort();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailSmtpAuth);
            props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", mailSmtpPort);

            //create the Session object
            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(props, authenticator);

            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            // Set cc: header field of the header.
            for (int i = 0; i < ccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
            }
            // Set bcc: header field of the header.
            for (int i = 0; i < bccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }

            //set email subject field
            message.setSubject(titulo);
            //set the content of the email message
            message.setContent(mensaje, "text/html; charset=utf-8");
            //send the email message
            Transport.send(message);

            result = Boolean.TRUE;
        } catch (Exception e) {
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return result;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean sendEmailWithFile(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile, String nameFile))">
    @Override
    public Boolean sendEmailWithFile(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile, String nameFile) {
        Boolean result = Boolean.FALSE;
        try {

            List<Emailconfiguration> emailconfigurationList = applicative.getEmailconfiguration();
            if (emailconfigurationList == null || emailconfigurationList.isEmpty()) {
                System.out.println("No hay configuracion de emails");
                return false;
            }

            Optional<Emailconfiguration> emailconfigurationOptional = emailconfigurationList.stream().filter(x -> x.getActive().equals(Boolean.TRUE)).findFirst();
            if (!emailconfigurationOptional.isPresent()) {
                System.out.println("No hay configuracion de emails activo");
                return false;
            }
            emailconfigurationOptional.get();

//cc, bcc, to multiples
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of toaddresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            InternetAddress[] bccAddress = new InternetAddress[bcc.length];

            // To get the array of bccaddresses
            for (int i = 0; i < bcc.length; i++) {
                bccAddress[i] = new InternetAddress(bcc[i]);
            }

            InternetAddress[] ccAddress = new InternetAddress[cc.length];

            // To get the array of ccaddresses
            for (int i = 0; i < cc.length; i++) {
                ccAddress[i] = new InternetAddress(cc[i]);
            }

            String from = emailconfigurationOptional.get().getEmail();
//            //provide Mailtrap's username
            final String username = emailconfigurationOptional.get().getEmail();
//            //provide Mailtrap's password
            final String password = Encryptor.decrypt(emailconfigurationOptional.get().getPassword(), secretKey,MessagesUtil.nameOfClassAndMethod());
//            //provide Mailtrap's host address
            String host = emailconfigurationOptional.get().getMailSmtpHost();
//            //configure Mailtrap's SMTP server details

            String mailSmtpAuth = emailconfigurationOptional.get().getMailSmtpAuth();
            String mailSmtpStarttlsEnable = emailconfigurationOptional.get().getMailSmtpStarttlsEnable();
            String mailSmtpPort = emailconfigurationOptional.get().getMailSmtpPort();

            Properties props = new Properties();
            props.put("mail.smtp.auth", mailSmtpAuth);
            props.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", mailSmtpPort);

            //create the Session object
            Authenticator authenticator = new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            };

            Session session = Session.getInstance(props, authenticator);

            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            // Set cc: header field of the header.
            for (int i = 0; i < ccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
            }
            // Set bcc: header field of the header.
            for (int i = 0; i < bccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.BCC, bccAddress[i]);
            }

            //set email subject field
            message.setSubject(titulo);

            //create the message body part
            BodyPart messageBodyPart = new MimeBodyPart();
            //set the actual message
            messageBodyPart.setText(mensaje);
            //create an instance of multipart object
            Multipart multipart = new MimeMultipart();
            //set the first text message part
            multipart.addBodyPart(messageBodyPart);
            //set the second part, which is the attachment
            messageBodyPart = new MimeBodyPart();
            String filename = pathFile;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(nameFile);
            multipart.addBodyPart(messageBodyPart);
            //send the entire message parts
            message.setContent(multipart);

            //send the email message
            Transport.send(message);

            result = Boolean.TRUE;
        } catch (Exception e) {
          MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + "error: " + e.getLocalizedMessage());
        }
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {

            @Override
            public Object call() throws Exception {

                sendEmail(emailreceptor, titulo, mensaje);

                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String emailemisor, String passwordemisor)">
    @Override
    public Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                sendEmail(to, cc, bcc, titulo, mensaje);
                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Future<String> sendEmailCccBccWithFileAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile,  String nameFile)">

    @Override
    public Future<String> sendEmailCccBccWithFileAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile, String nameFile) throws InterruptedException {

        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                sendEmailWithFile(to, cc, bcc, titulo, mensaje, pathFile, nameFile);
                completableFuture.complete("enviado");

                return null;
            }
        });

        return completableFuture;
    }// </editor-fold>

}
