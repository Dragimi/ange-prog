package models;

import java.util.ArrayList;

/**
 *
 */
public class Reiseagentur {

    private String namen;
    private String ustIdNr;
    private Adresse adresse;
    private ArrayList<Kunde> kunden;

    /**
     *
     * @param namen
     * @param ustIdNr
     * @param adresse
     */
    public Reiseagentur(String namen, String ustIdNr, Adresse adresse) {
        this.namen = namen;
        this.ustIdNr = ustIdNr;
        this.adresse = adresse;
        this.kunden = new ArrayList<Kunde>();
    }

    /**
     *
     * @param kunde
     * @return
     */
    public boolean addKunde(Kunde kunde) {
        this.kunden.add(kunde);
        return true;
    }

    @Override
    public String toString() {
        return "Reiseagentur{\n" +
                "\tnamen='" + namen + "',\n" +
                "\tustIdNr='" + ustIdNr + "',\n" +
                "\tadresse=" + adresse + ",\n" +
                "\tkundenanzahl=" + kunden.size() + "\n" +
                '}';
    }

    /**
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

    public ArrayList<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(ArrayList<Kunde> kunden) {
        this.kunden = kunden;
    }
}
