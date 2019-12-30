package models;

import utils.Utils;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Reservierung implements Serializable {

    protected String reservierungsNr;
    protected LocalDate datum;
    protected double summe;

    private Reservierung() {
    }

    public Reservierung(LocalDate datum, double summe) {
        this.reservierungsNr = Utils.generateRandomReservierungsNummer();
        this.datum = datum;
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
        } else if (r instanceof Hotelreservierung) {
            Hotelreservierung h = (Hotelreservierung) r;
            bemerkung = String.format("%s für %s", h.hotelname, h.reisedauer);
        }

        System.out.println(String.format("|%-16s|%-10s|%-10s|%-70s|",
                r.reservierungsNr,
                Utils.dateToStr(r.datum),
                r.summe,
                bemerkung));
    }

    @Override
    public String toString() {
        String bemerkung = "";
        if (this instanceof Flugreservierung) {
            Flugreservierung f = (Flugreservierung) this;
            bemerkung = String.format("%s, von %s nach %s", f.getFlugnr(), f.getAbflughafen(), f.getZielflughafen());
        } else if (this instanceof Hotelreservierung) {
            Hotelreservierung h = (Hotelreservierung) this;
            bemerkung = String.format("%s für %s", h.hotelname, h.reisedauer);
        }

        return String.format("%s, %s, %s, %s",
                this.reservierungsNr,
                Utils.dateToStr(this.datum),
                this.summe,
                bemerkung);
    }

    public String getReservierungsNr() {
        return reservierungsNr;
    }

    public void setReservierungsNr(String reservierungsNr) {
        this.reservierungsNr = reservierungsNr;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public double getSumme() {
        return summe;
    }

    public void setSumme(double summe) {
        this.summe = summe;
    }
}
