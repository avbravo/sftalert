/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.emails;

import java.util.List;

/**
 *
 * @author avbravo
 */
public class EmailRecipients {

// <editor-fold defaultstate="collapsed" desc="fields()">
    String[] to; // list of recipient email addresses
    String[] cc;
    String[] bcc;

    Integer index = 0;
    Integer indexcc = 0;
    Integer indexbcc = 0;
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" metodo()">
// </editor-fold>
    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndexcc() {
        return indexcc;
    }

    public void setIndexcc(Integer indexcc) {
        this.indexcc = indexcc;
    }

    public Integer getIndexbcc() {
        return indexbcc;
    }

    public void setIndexbcc(Integer indexbcc) {
        this.indexbcc = indexbcc;
    }

    public void divide(List<String> list) {

        try {

            Integer size = list.size();
            if (size <= 10) {
                to = new String[list.size() ];
                cc = new String[0];
                bcc = new String[0];
            } else {
                if (size > 10 && size <= 20) {
                    to = new String[10 ];
                    cc = new String[size - 10];
                    bcc = new String[0];
                } else {
                    to = new String[10 ];
                    cc = new String[10 ];
                    bcc = new String[size - 20];
                }
            }
            index = 0;
            indexcc = 0;
            indexbcc = 0;

            list.forEach((u) -> {

                if (index <= 10) {
                    to[index] = u;
                    index++;
                } else {
                    if (index > 10 && index <= 20) {
                        cc[indexcc] = u;
                        indexcc++;
                    } else {
                        bcc[indexbcc] = u;
                        indexbcc++;
                    }
                }

            });
        } catch (Exception e) {
            System.out.println("EmailRecipients.divide() "+e.getLocalizedMessage());
        }
    }
    
    
    
    

}
