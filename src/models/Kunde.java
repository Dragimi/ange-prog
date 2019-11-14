package models;

import utils.Utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Diese Klasse repräsentiert einen Kunden welche bis zu drei Bezahlmethoden hat.
 */
public abstract class Kunde {

    static final int MAX_NUMBER_OF_RESERVATIONS = 100;

    protected String kundenNummer;
    protected String anrede;
    protected String vorname;
    protected String nachname;
    protected Date geburtstag;
    protected Adresse adresse;
    protected String telefonNummer;
    protected String email;
    protected ArrayList<Bezahlmethode> bezahlmethoden;
    protected ArrayList<Reservierung> reservierungen;

    /**
     * Ein Kunde besteht aus einem Vornamen , Nachnamen, einer Anrede, einem Geburtsdatum,
     * einer Pflichtadresse, einer Telefonnr., und einer E-Maialdresse.
     * <br>
     * Um weitere Bezahlmethoden hinzuzfügen benutze, die Methode {@link Kunde#addZahlungsmethode(Bezahlmethode)}.
     *
     * @param anrede - Hr. oder Fr.
     * @param vorname -
     * @param nachname -
     * @param geburtstag -
     * @param adresse -
     * @param telefonNummer -
     * @param email -
     *
     * @see Bezahlmethode
     * @see Adresse
     */
    protected Kunde(String anrede, String vorname, String nachname, Date geburtstag, Adresse adresse, String telefonNummer, String email) {
        this.kundenNummer = Utils.generateRandomKundennummer();
        this.anrede = anrede;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
        this.adresse = adresse;
        this.telefonNummer = telefonNummer;
        this.email = email;
        this.bezahlmethoden = new ArrayList<>();
        this.reservierungen = new ArrayList<>();
    }

    abstract public String getName();
    abstract public boolean addZahlungsmethode(Bezahlmethode methode);

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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
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
