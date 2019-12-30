package models;

import utils.Input;

import java.io.Serializable;

/**
 * Adresse repräsentiert eine physiche Lokalisierung in einem Land.
 */
public class Adresse implements Serializable {

    private String strasse;
    private String hausnummer;
    private int plz;
    private String ort;

    private Adresse() {
    }

    /**
     * Jede Adresse besteht aus einer Strasse, einer Hausnummer, der Postleitzahl und einem Ort
     *
     * @param strasse
     * @param hausnummer
     * @param plz
     * @param ort
     */
    public Adresse(String strasse, String hausnummer, int plz, String ort) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.plz = plz;
        this.ort = ort;
    }

    public static Adresse readAdresseFromInput(Input input) {
        String strasse = input.read("Strasse: ");
        String hausnummer = input.read("Hausnummer: ");
        int plz = input.readInt("Plz: ", 10000, 99999);
        String ort = input.read("Ort");

        return new Adresse(strasse, hausnummer, plz, ort);
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + "', " +
                "hausnummer='" + hausnummer + "', " +
                "plz=" + plz + ", " +
                "ort='" + ort + "'}";
    }

    public String shortStringRepresentation() {
        return String.format("%s %s, %d %s", this.strasse, this.hausnummer, this.plz, this.ort);
    }

    /*
     * GETTER, SETTER
     */

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
