package models;

import java.time.LocalDate;

public class Flugreservierung extends Reservierung {

    private String flugnr;
    private String abflughafen;
    private String zielflughafen;

    public Flugreservierung(String reservierungsNr, LocalDate localDate, int summe, String flugnr, String abflughafen, String zielflughafen) {
        super(reservierungsNr, localDate, summe);
        this.flugnr = flugnr;
        this.abflughafen = abflughafen;
        this.zielflughafen = zielflughafen;
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
