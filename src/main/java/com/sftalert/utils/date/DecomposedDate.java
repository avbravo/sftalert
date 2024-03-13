/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sftalert.utils.date;

import java.util.Objects;

/**
 *
 * @author avbravo
 */
public class DecomposedDate {

    /**
     * Se usa para almacenar una fecha descompuesta
     */
    Integer year;
    Integer day;
    Integer month;
    Integer hour;
    Integer minute;
    Integer seconds;
    String nameOfMonth;

    public DecomposedDate() {
    }

    public DecomposedDate(Integer year, Integer day, Integer month, Integer hour, Integer minute, Integer seconds, String nameOfMonth) {
        this.year = year;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
        this.nameOfMonth = nameOfMonth;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DecomposedDate{");
        sb.append("year=").append(year);
        sb.append(", day=").append(day);
        sb.append(", month=").append(month);
        sb.append(", hour=").append(hour);
        sb.append(", minute=").append(minute);
        sb.append(", seconds=").append(seconds);
        sb.append(", nameOfMonth=").append(nameOfMonth);
        sb.append('}');
        return sb.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="Boolean equalsExcludeTime(DecomposedDate other)">
    public Boolean equalsExcludeTime(DecomposedDate other) {

        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day)) {
            return true;
        }
        return false;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean equalsIncludeTime(DecomposedDate other ,Boolean includeSeconds)">

    public Boolean equalsIncludeTime(DecomposedDate other, Boolean includeSeconds) {

        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.hour, other.hour)) {
            return false;
        }
        if (!Objects.equals(this.minute, other.minute)) {
            return false;
        }

        if (includeSeconds) {
            if (this.year.equals( other.year) && this.month.equals(other.month) && this.day.equals( other.day) && this.hour.equals(other.hour) && this.minute.equals(other.minute) && this.seconds.equals(seconds)) {
                return Boolean.TRUE;
            } else {
                if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour.equals(other.hour) && this.minute.equals(other.minute)) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean lessExcludeTime(DecomposedDate other)">
    public Boolean lessExcludeTime(DecomposedDate other) {

        if (this.year < other.year) {
            return Boolean.TRUE;
        }

        if (this.year.equals(other.year) && this.month < other.month) {

            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day < other.day) {

            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean lessIncludeTime(DecomposedDate other, Boolean includeSeconds)">
    public Boolean lessIncludeTime(DecomposedDate other, Boolean includeSeconds) {

        if (this.year < other.year) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month < other.month) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day < other.day) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour < other.hour) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour.equals(other.hour) && this.minute < other.minute) {
            return Boolean.TRUE;
        }
        if (includeSeconds) {
            if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour.equals(other.hour) && this.minute.equals(other.minute) && this.seconds < other.seconds) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean greaterExcludeTime(DecomposedDate other)">

    public Boolean greaterExcludeTime(DecomposedDate other) {

        if (this.year > other.year) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month > other.month) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day > other.day) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean greateIncludeTime(DecomposedDate other)">
    public Boolean greaterIncludeTime(DecomposedDate other, Boolean includeSeconds) {

        if (this.year > other.year) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month > other.month) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day > other.day) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour > other.day) {
            return Boolean.TRUE;
        }
        if (this.year.equals(other.year) && this.month.equals(other.month) && this.day.equals(other.day) && this.hour.equals( other.day) && this.minute > other.minute) {
            return Boolean.TRUE;
        }
        if (includeSeconds) {
            if (this.year.equals(other.year) && this.month.equals( other.month) && this.day.equals(other.day) && this.hour.equals(other.day) && this.minute.equals(other.minute) && this.seconds > other.seconds) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    // </editor-fold>
}
