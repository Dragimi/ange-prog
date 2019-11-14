package models;

import utils.Input;
import utils.Utils;

import java.util.Date;

public class Privatkunde extends Kunde {

    public Privatkunde(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse, String telefonNummer, String email) {
        super(anrede, vorname, nachname, geburtstag, adresse, telefonNummer, email);
    }

    public String getName() {
        return String.format("%s %s", this.vorname, this.nachname);
    }

    public boolean addZahlungsmethode(Bezahlmethode methode) {
        if (this.bezahlmethoden.size() < 3) {
            this.bezahlmethoden.add(methode);
            return true;
        }
        else {
            System.out.println("Kunde besitzt bereits 3 Bezahlmethoden.");
            return false;
        }
    }

    public static Privatkunde readPrivatKundeFromInput(Input input) {
        String anrede = input.read("Anrede (Hr. oder Fr.): ");
        String vorname = input.read("Vorname: ");
        String nachname = input.read("Nachname: ");
        Date geb = input.readDate("Geburtsdatum: ");
        Adresse adresse = Adresse.readAdresseFromInput(input);
        String telefonnummer = input.read("Telefonnummer: ");
        String email = input.read("Email: ");

        return new Privatkunde(anrede, vorname, nachname, geb, adresse, telefonnummer, email);
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
