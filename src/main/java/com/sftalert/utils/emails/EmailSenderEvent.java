/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.emails;

/**
 *
 * @author avbravo
 */
public class EmailSenderEvent {

    private EmailSender emailSender;

    public EmailSenderEvent() {
    }

    public EmailSenderEvent(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public EmailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

}
