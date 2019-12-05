package models;

import utils.Input;

import java.time.LocalDate;

public class Flugreservierung extends Reservierung {

    private String flugnr;
    private String abflughafen;
    private String zielflughafen;

    public Flugreservierung(LocalDate date, double summe, String flugnr, String abflughafen, String zielflughafen) {
        super(date, summe);
        this.flugnr = flugnr;
        this.abflughafen = abflughafen;
        this.zielflughafen = zielflughafen;
    }

    public static Flugreservierung readFlugreservierungFromInput(Input input) {
        LocalDate datum = input.readDate("Reservierungsdatum: ");
        int summe = input.readInt("Summe: ", 0, Integer.MAX_VALUE);
        String flugnr = input.read("Flugnr.");
        String abflughafen = input.read("Abflughafen: ");
        String zielflughafen = input.read("Zeilflughafen: ");

        return new Flugreservierung(datum, summe, flugnr, abflughafen, zielflughafen);
    }

    public String getFlugnr() {
        return flugnr;
    }

    public void setFlugnr(String flugnr) {
        this.flugnr = flugnr;
    }

    public String getAbflughafen() {
        return abflughafen;
    }

    public void setAbflughafen(String abflughafen) {
        this.abflughafen = abflughafen;
    }

    public String getZielflughafen() {
        return zielflughafen;
    }

    public void setZielflughafen(String zielflughafen) {
        this.zielflughafen = zielflughafen;
    }
}
