package models;

import java.time.Duration;
import java.time.LocalDate;

public class Hotelreservierung extends Reservierung {

    String hotelname;
    Duration reisedauer;

    public Hotelreservierung(String reservierungsNr, LocalDate localDate, int summe, String hotelname, Duration reisedauer) {
        super(reservierungsNr, localDate, summe);
        this.hotelname = hotelname;
        this.reisedauer = reisedauer;
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
