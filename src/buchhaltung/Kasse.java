package buchhaltung;

import models.Bezahlmethode;

import java.io.Serializable;
import java.util.Observable;

public class Kasse extends Observable implements Serializable {

    private static Kasse instance;
    private Bezahlmethode bezahlmethode;

    public static Kasse getInstance() {
        if ( instance == null) {
            instance = new Kasse();
        }
        return instance;
    }

    public Bezahlmethode getBezahlmethode() {
        return bezahlmethode;
    }

    public void setBezahlmethode(Bezahlmethode bezahlmethode) {
        this.bezahlmethode = bezahlmethode;
    }

    public void bezahlen(double betrag) {
        System.out.printf("Der Betrag %.2f Euro wird mit der Bezahlstrategie '%s' bezahlt.\n", betrag, this.bezahlmethode.getBezeichnung());
        Zahlung zahlung = new Zahlung(betrag, this.bezahlmethode);
        setChanged();
        notifyObservers(zahlung);
    }

}
