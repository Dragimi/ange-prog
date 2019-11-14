package models;

import java.util.Date;

public class Privatkunden extends Kunde {

    public Privatkunden(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse, String telefonNummer, String email) {
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

    @Override
    public String toString() {
        return "Privatkunden{" +
                "kundenNummer='" + kundenNummer + '\'' +
                ", anrede='" + anrede + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag=" + geburtstag +
                ", adresse=" + adresse +
                ", telefonNummer='" + telefonNummer + '\'' +
                ", email='" + email + '\'' +
                ", bezahlmethoden=" + bezahlmethoden.size() +
                '}';
    }
}
