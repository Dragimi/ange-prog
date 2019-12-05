package models;

import utils.Input;
import utils.Utils;

import java.time.LocalDate;

public class Privatkunde extends Kunde {

    public Privatkunde(String anrede, String vorname, String nachname, LocalDate geburtstag, Adresse adresse, String telefonNummer, String email) {
        super(anrede, vorname, nachname, geburtstag, adresse, telefonNummer, email);
    }

    public static Privatkunde readPrivatKundeFromInput(Input input) {
        String anrede = input.read("Anrede (Hr. oder Fr.): ");
        String vorname = input.read("Vorname: ");
        String nachname = input.read("Nachname: ");
        LocalDate geb = input.readDate("Geburtsdatum: ");
        Adresse adresse = Adresse.readAdresseFromInput(input);
        String telefonnummer = input.read("Telefonnummer: ");
        String email = input.read("Email: ");

        return new Privatkunde(anrede, vorname, nachname, geb, adresse, telefonnummer, email);
    }

    public String getName() {
        return String.format("%s %s", this.vorname, this.nachname);
    }

    @Override
    public String toString() {
        return "Privatkunden{" +
                "kundenNummer='" + kundenNummer + '\'' +
                ", anrede='" + anrede + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag=" + Utils.dateToStr(geburtstag) +
                ", adresse=" + adresse +
                ", telefonNummer='" + telefonNummer + '\'' +
                ", email='" + email + '\'' +
                ", bezahlmethoden=" + bezahlmethoden.size() +
                ", reservierungen=" + reservierungen.size() +
                '}';
    }
}
