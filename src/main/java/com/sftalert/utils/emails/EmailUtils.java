/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sftalert.utils.emails;

import com.sftalert.utils.emails.EmailRecipients;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class EmailUtils {
     // <editor-fold defaultstate="collapsed" desc="Boolean divideDestinatary(List<User> list)()">
    /**
     * Divide de una lista los emails
     *
     * @param list
     * @return
     */
    public static EmailRecipients divideDestinatary(List<String> list) {
        EmailRecipients emailRecipients = new EmailRecipients();
        try {
            ////Divide para las copias y bcc,cc
            emailRecipients.divide(list);

        } catch (Exception e) {
            System.out.println("divideDestinatary() " + e.getLocalizedMessage());
        }
        return emailRecipients;
    }
    // </editor-fold>
}
