import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class Utils {

    /**
     *
     * @param date
     * @return
     */
    public static Date strToDate(String date) {
        try{
            return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN).parse(date);
        } catch (ParseException pe) {
            System.out.println("Der eingegebene Geburtstag ist kein gültiger.");
            return strToDate("01.01.1970");
        }
    }

    public static String dateToStr(Date date) {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

}
