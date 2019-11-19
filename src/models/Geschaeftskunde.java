package models;

import utils.Input;
import utils.Utils;

import java.util.Date;

public class Geschaeftskunde extends Kunde {

    String firmenname;

    public Geschaeftskunde(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse, String telefonNummer, String email, String firmenname) {
        super(anrede, vorname, nachname, geburtstag, adresse, telefonNummer, email);
        this.firmenname = firmenname;
    }

    public String getName() {
        return String.format("%s %s - %s", this.vorname, this.nachname, this.firmenname);
    }

    public boolean addZahlungsmethode(Bezahlmethode methode) {
        if (this.bezahlmethoden.size() >= 1) {
            System.out.println("Es ist bereits eine Bezahlmethode hinzugefügt worden. Maximale Anzahl erreicht.");
            return false;
        }
        if (!methode.getBezeichnung().equals(PaymentType.RECHNUNG)) {
            System.out.println("Geschäftskunden können nur RECHNUNG als Bezahlmethode wählen.");
            return false;
        }
        this.bezahlmethoden.add(methode);
        return true;
    }

    public static Geschaeftskunde readGeschaeftsKundeFromInput(Input input) {
        String anrede = input.read("Anrede (Hr. oder Fr.): ");
        String firmenname = input.read("Firmenname: ");
        String vorname = input.read("Vorname: ");
        String nachname = input.read("Nachname: ");
        Date geb = input.readDate("Geburtsdatum: ");
        Adresse adresse = Adresse.readAdresseFromInput(input);
        String telefonnummer = input.read("Telefonnummer: ");
        String email = input.read("Email: ");

        return new Geschaeftskunde(anrede, vorname, nachname, geb, adresse, telefonnummer, email, firmenname);
    }

    @Override
    public String toString() {
        return "Geschaeftskunden{" +
                "firmenname='" + firmenname + '\'' +
                ", kundenNummer='" + kundenNummer + '\'' +
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
