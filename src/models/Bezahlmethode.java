package models;


/**
 * Diese Klasse repraesentiert eine Bezahlmethode.
 * {@link Kunde Kunden} koennen bis zu drei Bezahlmethoden besitzen.
 */
public class Bezahlmethode {

    private PaymentType bezeichnung;
    private String beschreibung;

    /**
     * Jede Bezahlmethode besteht aus einer Bezeichnung und einer kurzen Beschreibung
     * @param bezeichnung
     * @param beschreibung
     */
    public Bezahlmethode(PaymentType bezeichnung, String beschreibung) {
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "Bezahlmethode{" +
                " bezeichnung='" + bezeichnung + "'," +
                " beschreibung='" + beschreibung + "'" +
                '}';
    }

    /*
     * GETTER, SETTER
     */

    public PaymentType getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(PaymentType bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
