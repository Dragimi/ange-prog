package models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Diese Klasse symbolisiert eine Reiseagentur.
 * Jede Reiseagentur besitzt einen Namen, eine Umsatzsteuernummer und eine {@link Adresse},
 * <br>
 * Zudem kann eine Agentur {@link Kunde Kunden} haben.
 *
 * @see Adresse
 * @see Kunde
 */
public class Reiseagentur {

    private String namen;
    private String ustIdNr;
    private Adresse adresse;
    private HashMap<String, Kunde> kunden;

    /**
     * Jede Agentur benoetigt eine namen, eine umsatzsteuernummer und eine Adresse
     *
     * @param namen
     * @param ustIdNr
     * @param adresse
     */
    public Reiseagentur(String namen, String ustIdNr, Adresse adresse) {
        this.namen = namen;
        this.ustIdNr = ustIdNr;
        this.adresse = adresse;
        this.kunden = new HashMap<>();
    }

    /**
     * Fuegt einen {@link Kunde Kunden} in die Liste hinzu.
     * @param kunde
     * @return
     */
    public boolean addKunde(Kunde kunde) {
        this.kunden.put(kunde.kundenNummer, kunde);
        return true;
    }

    /**
     * Ueberschreibt die toString-Methode
     * @return String
     */
    @Override
    public String toString() {
        return "Reiseagentur{\n" +
                "\tnamen='" + namen + "',\n" +
                "\tustIdNr='" + ustIdNr + "',\n" +
                "\tadresse=" + adresse + ",\n" +
                "\tkundenanzahl=" + kunden.size() + "\n" +
                '}';
    }

    /*
     * GETTER, SETTER
     */

    public String getNamen() {
        return namen;
    }

    public void setNamen(String namen) {
        this.namen = namen;
    }

    public String getUstIdNr() {
        return ustIdNr;
    }

    public void setUstIdNr(String ustIdNr) {
        this.ustIdNr = ustIdNr;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public HashMap<String, Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(HashMap<String, Kunde> kunden) {
        this.kunden = kunden;
    }
}
