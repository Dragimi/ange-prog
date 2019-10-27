import models.Adresse;
import models.Bezahlmethode;
import models.Kunde;
import models.Reiseagentur;

public class Main {

    public static void main(String[] args) {
        Reiseagentur magic = new Reiseagentur(
                "Magic Holidays Reiseagentur",
                "DE812524001",
                new Adresse("Hauptstraße", "5a", 10559, "Berlin")
        );

        Kunde kunde1 = new Kunde("Hr.",
                "Max",
                "Mustermann",
                Utils.strToDate("02.10.1992"),
                new Adresse("", "", 12345, "Berlin"),
                "",
                "");
        kunde1.addZahlungsmethode(new Bezahlmethode("PayPal", "Konto: " + kunde1.getEmail()));
        kunde1.addZahlungsmethode(new Bezahlmethode("VISA", "4235 7453 1234 7456"));

        Kunde kunde2 = new Kunde("Fr.",
                "Melanie",
                "Mustermann",
                Utils.strToDate("14.08.1994"),
                new Adresse("", "", 12345, "Berlin"),
                "",
                "");
        kunde2.addZahlungsmethode(new Bezahlmethode("VISA", "4438 8123 9735 2345"));

        Kunde kunde3 = new Kunde("Prof. Dr.",
                "Andreas",
                "Müller",
                Utils.strToDate("12.06.1980"),
                new Adresse("", "", 12345, "Berlin"),
                "",
                "");
        kunde3.addZahlungsmethode(new Bezahlmethode("MasterCard", "5729 8023 9829 8235"));

        magic.addKunde(kunde1);
        magic.addKunde(kunde2);
        magic.addKunde(kunde3);


        System.out.println(magic);
        System.out.println(kunde1);
        System.out.println(kunde2);
        System.out.println(kunde3);
    }

}
