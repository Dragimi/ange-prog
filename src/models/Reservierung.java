package models;

import utils.Utils;

import java.time.LocalDate;
import java.util.Date;

public abstract class Reservierung {

    protected String reservierungsNr;
    protected Date localDate;
    protected double summe;

    public Reservierung(Date localDate, double summe) {
        this.reservierungsNr = Utils.generateRandomReservierungsNummer();
        this.localDate = localDate;
        this.summe = summe;
    }

    public static void printLongTableHeader() {
        System.out.println("|Reservierungsnr.|Datum     |Summe     |Bemerkung                                                             |");
        System.out.println("|----------------+----------+----------+----------------------------------------------------------------------|");
    }

    public static void printLongTableRow(Reservierung r) {
        String bemerkung = "";
        if (r instanceof Flugreservierung) {
            Flugreservierung f = (Flugreservierung) r;
            bemerkung = String.format("%s, von %s nach %s", f.getFlugnr(), f.getAbflughafen(), f.getZielflughafen());
        }
        else if (r instanceof Hotelreservierung){
            Hotelreservierung h = (Hotelreservierung) r;
            bemerkung = String.format("%s für %s", h.hotelname, h.reisedauer);
        }

        System.out.println(String.format("|%-16s|%-10s|%-10s|%-70s|",
                r.reservierungsNr,
                Utils.dateToStr(r.localDate),
                r.summe,
                bemerkung));
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

    public double getSumme() {
        return summe;
    }

    public void setSumme(double summe) {
        this.summe = summe;
    }
}
