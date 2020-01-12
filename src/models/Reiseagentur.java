package models;

import buchhaltung.Auditing;
import buchhaltung.Buchhaltung;
import buchhaltung.Kasse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

/**
 * Diese Klasse symbolisiert eine Reiseagentur.
 * Jede Reiseagentur besitzt einen Namen, eine Umsatzsteuernummer und eine {@link Adresse},
 * <br>
 * Zudem kann eine Agentur {@link Kunde Kunden} haben.
 *
 * @see Adresse
 * @see Kunde
 */
public class Reiseagentur implements Serializable {

    private String namen;
    private String ustIdNr;
    private Adresse adresse;
    private HashMap<String, Kunde> kunden;

    private Kasse kasse;
    private Buchhaltung buchhaltung;
    private Auditing auditing;

    private Reiseagentur() {
    }

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
        this.kasse = Kasse.getInstance();
        this.buchhaltung = Buchhaltung.getInstance();
        this.auditing = Auditing.getInstance();
    }

    public void initObserver() {
        kasse.deleteObservers();
        kasse.addObserver(buchhaltung);
        kasse.addObserver(auditing);
    }
    /**
     * Fuegt einen {@link Kunde Kunden} in die Liste hinzu.
     *
     * @param kunde
     * @return
     */
    public boolean addKunde(Kunde kunde) {
        this.kunden.put(kunde.kundenNummer, kunde);
        return true;
    }

    /**
     * Ueberschreibt die toString-Methode
     *
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

    public Kasse getKasse() {
        return kasse;
    }

    public void setKasse(Kasse kasse) {
        this.kasse = kasse;
    }

    public Buchhaltung getBuchhaltung() {
        return buchhaltung;
    }

    public void setBuchhaltung(Buchhaltung buchhaltung) {
        this.buchhaltung = buchhaltung;
    }

    public Auditing getAuditing() {
        return auditing;
    }

    public void setAuditing(Auditing auditing) {
        this.auditing = auditing;
    }
}
