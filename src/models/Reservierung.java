package models;

import utils.Utils;

import java.time.LocalDate;
import java.util.Date;

public abstract class Reservierung {

    protected String reservierungsNr;
    protected Date localDate;
    protected int summe;

    public Reservierung(Date Date, int summe) {
        this.reservierungsNr = Utils.generateRandomReservierungsNummer();
        this.localDate = localDate;
        this.summe = summe;
    }

    public String getReservierungsNr() {
        return reservierungsNr;
    }

    public void setReservierungsNr(String reservierungsNr) {
        this.reservierungsNr = reservierungsNr;
    }

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public int getSumme() {
        return summe;
    }

    public void setSumme(int summe) {
        this.summe = summe;
    }
}
