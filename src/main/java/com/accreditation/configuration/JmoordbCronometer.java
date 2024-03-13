/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accreditation.configuration;

/**
 *
 * @author avbravo
 */
public class JmoordbCronometer {

    public static long startTime;
    public static long endTime;

    public static long startCronometer(String nameOfCalssAndMethod, String... message) {
        try {
             String msg="";
            if (message.length != 0) {
                msg = message[0];
            }   
            System.out.println("\t::::::::::::::::::::[Iniciando]:::::::::::::::::::::::::");
            System.out.println("\t        [Iniciando cronometro]:" + nameOfCalssAndMethod + " "+msg);
            System.out.println("\t:::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

            startTime = System.nanoTime();
        } catch (Exception e) {
            System.out.println("start() " + e.getLocalizedMessage());
        }

        return startTime;
    }
    // <editor-fold defaultstate="collapsed" desc="static long nanoSecondsNow()">
/**
 * 
 * @return nanosegundos actuales
 */
    public static long nanoSecondsNow() {
        Long result =0L;
        try {
            
            result = System.nanoTime();
        } catch (Exception e) {
            System.out.println("start() " + e.getLocalizedMessage());
        }

        return result;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="static long durations(String nameOfCalssAndMethod, String... message)">

    public static long endCronometer(String nameOfCalssAndMethod, String... message) {
        long durationInMilliseconds = 0L;
        try {
             String msg="";
            if (message.length != 0) {
                msg = message[0];
            }    
            endTime = System.nanoTime();
            long duration = endTime - startTime;
            durationInMilliseconds = duration / 1_000_000;
            System.out.println("\t::::::::::::::::::[Resultado]:::::::::::::::::::::::::::");
            System.out.println("\t        [Detenido cronometro]:" + nameOfCalssAndMethod);
            System.out.println("\t        [Duración]: " + durationInMilliseconds + " (ms) "+msg);
            System.out.println("\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        } catch (Exception e) {
            System.out.println("start() " + e.getLocalizedMessage());
        }

        return durationInMilliseconds;

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" static long durations(String nameOfCalssAndMethod, long endTime, String... message)">

    public static long endCronometer(String nameOfCalssAndMethod, long endTime, String... message) {
        long durationInMilliseconds = 0L;
        try {
            String msg="";
            if (message.length != 0) {
                msg = message[0];
            }    
            endTime = System.nanoTime();
            long duration = endTime - startTime;
            durationInMilliseconds = duration / 1_000_000;
            System.out.println("\t::::::::::::::::::[Resultado]:::::::::::::::::::::::::::");
            System.out.println("\t        [Detenido cronometro]:" + nameOfCalssAndMethod);
            System.out.println("\t        [Duración]: " + durationInMilliseconds + " (ms) "+msg );
            System.out.println("\t::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        } catch (Exception e) {
            System.out.println("start() " + e.getLocalizedMessage());
        }

        return durationInMilliseconds;

    }
// </editor-fold>
    
    
    
    
    public static long diference(long start, long end){
        Long result =0L;
        try {
    
            long duration = end - start;
            result= duration / 1_000_000;
        } catch (Exception e) {
             System.out.println("dif()" + e.getLocalizedMessage());
        }
        return result;
    }
    public static long getStartTime() {
        return startTime;
    }

    public static void setStartTime(long startTime) {
        JmoordbCronometer.startTime = startTime;
    }

    public static long getEndTime() {
        return endTime;
    }

    public static void setEndTime(long endTime) {
        JmoordbCronometer.endTime = endTime;
    }
    
    

}
