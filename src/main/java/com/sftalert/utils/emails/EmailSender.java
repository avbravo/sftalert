/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.emails;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avbravo
 */
public class EmailSender {

    private String header;
    private String messages;
  
    List<String> emailList = new ArrayList<>();
  private String pathFile;
  private String nameFile; 
  
    public EmailSender() {
    }

    public EmailSender(String header, String messages, String pathFile, String nameFile,List<String> emailList ) {
        this.header = header;
        this.messages = messages;
        this.pathFile = pathFile;
        this.nameFile = nameFile;
        this.emailList = emailList ;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

   
    
    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

   

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<String> emailList) {
        this.emailList = emailList;
    }

    @Override
    public String toString() {
        return "EmailSender{" + "header=" + header + ", messages=" + messages + ", emailList=" + emailList + ", pathFile=" + pathFile + ", nameFile=" + nameFile + '}';
    }

    
    
    
    
    public static class Builder {

        private String header;
        private String messages;
        private String pathFile;
        List<String> emailList = new ArrayList<>();
          private String nameFile; 

        public Builder header(String header) {
            this.header = header;
            return this;
        }
        public Builder nameFile(String nameFile) {
            this.nameFile = nameFile;
            return this;
        }
        public Builder pathFile(String pathFile) {
            this.pathFile = pathFile;
            return this;
        }

        public Builder messages(String messages) {
            this.messages = messages;
            return this;
        }

        public Builder emailList(List<String> emailList) {
            this.emailList = emailList;
            return this;
        }

        public EmailSender build() {
            return new EmailSender(header, messages, pathFile, nameFile, emailList);
        }

    }

}
