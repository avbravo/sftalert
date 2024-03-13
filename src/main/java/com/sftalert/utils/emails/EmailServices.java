/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sftalert.utils.emails;

import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author avbravo
 */
public interface EmailServices {
    public Boolean sendEmail(String emailDestinatario, String titulo, String mensaje);
    public Boolean sendEmail(String titulo, String mensaje, List<String> emailList);
    public Boolean sendEmail(String[] to, String[] cc, String[] bcc, String titulo, String mensaje) ;
    public Boolean sendEmailWithFile(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile,  String nameFile) ;
    public Future<String> sendEmailCccBccAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje) throws InterruptedException ;
    public Future<String> sendEmailCccBccWithFileAsync(String[] to, String[] cc, String[] bcc, String titulo, String mensaje, String pathFile,  String nameFile) throws InterruptedException ;
   public Future<String> sendEmailAsync(String emailreceptor, String titulo, String mensaje) throws InterruptedException ;
}
