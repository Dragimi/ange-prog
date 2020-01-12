package buchhaltung;

import models.Bezahlmethode;

import java.io.Serializable;

public class Zahlung implements Serializable {

    private double betrag;
    private Bezahlmethode bezahlmethode;

    public Zahlung(double betrag, Bezahlmethode bezahlmethode) {
        this.betrag = betrag;
        this.bezahlmethode = bezahlmethode;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    public Bezahlmethode getBezahlmethode() {
        return bezahlmethode;
    }

    public void setBezahlmethode(Bezahlmethode bezahlmethode) {
        this.bezahlmethode = bezahlmethode;
    }
}
