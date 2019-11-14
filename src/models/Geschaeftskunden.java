package models;

import java.util.Date;

public class Geschaeftskunden extends Kunde {

    String firmenname;

    public Geschaeftskunden(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse, String telefonNummer, String email, String firmenname) {
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

    @Override
    public String toString() {
        return "Geschaeftskunden{" +
                "firmenname='" + firmenname + '\'' +
                ", kundenNummer='" + kundenNummer + '\'' +
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
