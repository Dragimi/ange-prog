package models;

import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    protected HashMap<String, Reservierung> reservierungen;

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
        this.reservierungen = new HashMap<>();
    }

    abstract public String getName();
    abstract public boolean addZahlungsmethode(Bezahlmethode methode);

    public void addReservierung(Reservierung reservierung) {
        this.reservierungen.put(reservierung.reservierungsNr, reservierung);
    }

    public static void printShortTableHeader() {
        System.out.println(String.format("|Nr.         |Vorname        |Nachname       |"));
        System.out.println(String.format("+------------+---------------+---------------+"));
    }

    public static void printShortTableRow(Kunde k) {
        System.out.println(String.format("|%-12s|%-15s|%-15s|", k.kundenNummer, k.vorname, k.nachname));
    }

    public static void printLongTableHeader() {
        System.out.println(String.format("|Nr.         " +
                "|Typ       " +
                "|Firmenname          " +
                "|Anrede " +
                "|Vorname        " +
                "|Nachname       " +
                "|Geburtstag" +
                "|Adresse                                 " +
                "|Telefonnummer       " +
                "|Email                         " +
                "|Bezahlmethoden" +
                "|Reservierungen|"));
        System.out.println(String.format("+------------" +
                "+----------" +
                "+--------------------" +
                "+-------" +
                "+---------------" +
                "+---------------" +
                "+----------" +
                "+----------------------------------------" +
                "+--------------------" +
                "+------------------------------" +
                "+--------------" +
                "+--------------|"));
    }

    public static void printLongTableRow(Kunde k) {
        System.out.println(String.format("|%-12s|%-10s|%-20s|%-7s|%-15s|%-15s|%-10s|%-40s|%-20s|%-30s|%-14s|%-14s|", k.kundenNummer,
                (k instanceof Privatkunde) ? "privat" : "geschäft",
                (k instanceof Geschaeftskunde) ? ((Geschaeftskunde) k).firmenname : "",
                k.anrede,
                k.vorname,
                k.nachname,
                Utils.dateToStr(k.geburtstag),
                k.adresse.shortStringRepresentation(),
                k.telefonNummer,
                k.email,
                k.bezahlmethoden.size(),
                k.reservierungen.size()));
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

    public HashMap<String, Reservierung> getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(HashMap<String, Reservierung> reservierungen) {
        this.reservierungen = reservierungen;
    }
}
