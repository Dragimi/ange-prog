package models;

import utils.Utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Diese Klasse repräsentiert einen Kunden welche bis zu drei Bezahlmethoden hat.
 */
public class Kunde {

    private String kundenNummer;
    private String anrede;
    private String vorname;
    private String nachname;
    private Date geburtstag;
    private Adresse adresse1;
    private Adresse adresse2;
    private String telefonNummer;
    private String email;
    private ArrayList<Bezahlmethode> bezahlmethoden;

    /**
     * Ein Kunde besteht aus einem Vornamen , Nachnamen, einer Anrede, einem Geburtsdatum,
     * einer Pflichtadresse, einer Telefonnr., und einer E-Maialdresse.
     * <br>
     * Um die zweite Addresse hinzuzufügen, benutze die Methode {@link Kunde#setAdresse2(Adresse)}.
     * <br>
     * Um weitere Bezahlmethoden hinzuzfügen benutze, die Methode {@link Kunde#addZahlungsmethode(Bezahlmethode)}.
     *
     * @param anrede - Hr. oder Fr.
     * @param vorname -
     * @param nachname -
     * @param geburtstag -
     * @param adresse1 -
     * @param telefonNummer -
     * @param email -
     *
     * @see Bezahlmethode
     * @see Adresse
     */
    public Kunde(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse1, String telefonNummer, String email) {
        this.kundenNummer = Utils.generateRandomKundennummer();
        this.anrede = anrede;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.adresse1 = adresse1;
        this.telefonNummer = telefonNummer;
        this.email = email;
        this.bezahlmethoden = new ArrayList<Bezahlmethode>();
    }

    /**
     *
     * @param methode
     * @return
     */
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
        return "Kunde {\n" +
                "\tkundenNummer='" + kundenNummer + "',\n" +
                "\tanrede='" + anrede + "',\n" +
                "\tvorname='" + vorname + "',\n" +
                "\tnachname='" + nachname + "',\n" +
                "\tgeburtstag=" + Utils.dateToStr(geburtstag) + "',\n" +
                "\tadresse1=" + adresse1 + ",\n" +
                "\tadresse2=" + adresse2 + ",\n" +
                "\ttelefonNummer='" + telefonNummer + "',\n" +
                "\temail='" + email + "',\n" +
                "\tbezahlmethodenAnzahl=" + bezahlmethoden.size() + "\n" +
                '}';
    }

    /*
     * Getter, Setter
     */

    public String getKundenNummer() {
        return kundenNummer;
    }

    public void setKundenNummer(String kundenNummer) {
        this.kundenNummer = kundenNummer;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(Date geburtstag) {
        this.geburtstag = geburtstag;
    }

    public Adresse getAdresse1() {
        return adresse1;
    }

    public void setAdresse1(Adresse adresse1) {
        this.adresse1 = adresse1;
    }

    public Adresse getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(Adresse adresse2) {
        this.adresse2 = adresse2;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Bezahlmethode> getBezahlmethoden() {
        return bezahlmethoden;
    }

    public void setBezahlmethoden(ArrayList<Bezahlmethode> bezahlmethoden) {
        this.bezahlmethoden = bezahlmethoden;
    }
}
