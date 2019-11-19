package models;

import utils.Input;
import utils.Utils;

import java.time.Duration;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;


public class Hotelreservierung extends Reservierung {

    String hotelname;
    Duration reisedauer;

    public Hotelreservierung(Date date, double summe, String hotelname, Duration reisedauer) {
        super(date, summe);
        this.hotelname = hotelname;
        this.reisedauer = reisedauer;
    }

    public static Hotelreservierung readHotelreservierungFromInput(Input input) {
        Date datum = input.readDate("Reservierungsdatum: ");
        int summe = input.readInt("Summe: ",0, Integer.MAX_VALUE);
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
