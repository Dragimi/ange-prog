package buchhaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Buchhaltung implements Observer, Serializable {

    private static Buchhaltung instance;
    private ArrayList<Zahlung> zahlungen = new ArrayList<>();

    public static Buchhaltung getInstance() {
        if (instance == null) {
            instance = new Buchhaltung();
        }
        return instance;
    }

    public ArrayList<Zahlung> getZahlungen() {
        return zahlungen;
    }

    public void setZahlungen(ArrayList<Zahlung> zahlungen) {
        this.zahlungen = zahlungen;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Zahlung)){
            return;
        }
        Zahlung z = (Zahlung) arg;
        zahlungen.add(z);
    }
}
