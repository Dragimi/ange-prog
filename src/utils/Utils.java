package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Hier werden ein paar hilfreiche Methoden gesammmelt
 */
public class Utils {

    /**
     * Wandelt ein String im Format dd.MM.yyyy in eine {@link Date}-Instanz um.
     * Falls das Datum in einem falschen Format eingegebene wurde, wird der 01.01.1970 zurueckgegeben.
     * (Todo hier eine exception werden und abfangen)
     * @param date
     * @return
     */
    public static Date strToDate(String date) {
        try{
            return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).parse(date);
        } catch (ParseException pe) {
            System.out.println("Das eingegebene Datum entspricht nicht dem richtigen Format.");
            return strToDate("01.01.1970");
        }
    }

    /**
     * Gibt das Datum im ueblichen deutschen Format dd.MM.yyyy als String zurueck.
     * @param date
     * @return dd.MM.yyyy Reprasentation eines Datums
     */
    public static String dateToStr(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    /**
     * Generiert eine zufaellige Kundennummer nach dem Format K-xxx-xxx
     * @return zufaellige Kundennummer
     */
    public static String generateRandomKundennummer() {
        return generateRandomId("K");
    }

    public static String generateRandomReservierungsNummer() {
        return generateRandomId("R");
    }

    private static String generateRandomId(String prefix) {
        return String.format("%s-%03d-%03d",
                prefix,
                (new Random()).nextInt(999) + 1,
                (new Random()).nextInt(999) + 1);
    }
}
