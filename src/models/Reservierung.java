package models;

import java.time.LocalDate;

public abstract class Reservierung {

    protected String reservierungsNr;
    protected LocalDate localDate;
    protected int summe;

    public Reservierung(String reservierungsNr, LocalDate localDate, int summe) {
        this.reservierungsNr = reservierungsNr;
        this.localDate = localDate;
        this.summe = summe;
    }

    public String getReservierungsNr() {
        return reservierungsNr;
    }

    public void setReservierungsNr(String reservierungsNr) {
        this.reservierungsNr = reservierungsNr;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getSumme() {
        return summe;
    }

    public void setSumme(int summe) {
        this.summe = summe;
    }
}
