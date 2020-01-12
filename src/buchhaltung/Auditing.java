package buchhaltung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Auditing implements Observer, Serializable {

    private static Auditing instance;
    private ArrayList<Zahlung> zahlungen = new ArrayList<>();

    public static Auditing getInstance() {
        if (instance == null) {
            instance = new Auditing();
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
        if (z.getBetrag() > 1000) {
            zahlungen.add(z);
        }
    }
}
