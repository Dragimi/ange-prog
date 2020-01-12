package models;

import utils.Input;

import java.time.Duration;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class Hotelreservierung extends Reservierung {

    String hotelname;
    Duration reisedauer;

    public Hotelreservierung(LocalDate date, double summe, String hotelname, Duration reisedauer) {
        super(date, summe);
        this.hotelname = hotelname;
        this.reisedauer = reisedauer;
    }

    public static Hotelreservierung readHotelreservierungFromInput(Input input) {
        LocalDate datum = input.readDate("Reservierungsdatum: ");
        double summe = input.readDouble("Summe: ", 0, Double.MAX_VALUE);
        String hotelname = input.read("Hotelname: ");
        int duration = input.readInt("Reisedauer (in Tagen): ", 1, Integer.MAX_VALUE);
        return new Hotelreservierung(datum, summe, hotelname, Duration.of(duration, DAYS));
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public Duration getReisedauer() {
        return reisedauer;
    }

    public void setReisedauer(Duration reisedauer) {
        this.reisedauer = reisedauer;
    }
}
