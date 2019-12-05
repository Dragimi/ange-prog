package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

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
    public static LocalDate strToDate(String date) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException pe) {
            System.out.println("Das eingegebene Datum entspricht nicht dem richtigen Format.");
            return strToDate("01.01.1970");
        }
    }

    /**
     * Gibt das Datum im ueblichen deutschen Format dd.MM.yyyy als String zurueck.
     * @param date
     * @return dd.MM.yyyy Reprasentation eines Datums
     */
    public static String dateToStr(LocalDate date) {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date);
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

    public static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValues(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
