import models.*;
import utils.Input;


public class Main {

    public static void main(String[] args) {
        Reiseagentur magic = new Reiseagentur(
                "Magic Holidays Reiseagentur",
                "DE812524001",
                new Adresse("Hauptstraße", "5a", 10559, "Berlin")
        );
/*
        Kunde kunde1 = new Privatkunden("Hr.",
                "Max",
                "Mustermann",
                Utils.strToDate("02.10.1992"),
                new Adresse("Julius-Leber-Str.", "234c", 38531, "Hamburg"),
                "0176 123 452 12",
                "max.mustermann@gmail.com");
        kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.PAYPAL, "Konto: " + kunde1.getEmail()));
        kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4235 7453 1234 7456"));

        Kunde kunde2 = new Privatkunden("Fr.",
                "Melanie",
                "Mustermann",
                Utils.strToDate("14.08.1994"),
                new Adresse("Ernst-Ruska-Ufer", "14", 40124, "München"),
                "0163 646 123 24",
                "m.m@yahoo.de");
        kunde2.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4438 8123 9735 2345"));

        Kunde kunde3 = new Geschaeftskunden("Prof. Dr.",
                "Andreas",
                "Müller",
                Utils.strToDate("12.06.1980"),
                new Adresse("Straße des 17. Juni", "135", 10942, "Berlin"),
                "0177 123 342 12",
                "andreas.mueller@gmx.net",
                "Spotify Inc.");
        kunde3.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Email"));

        magic.addKunde(kunde1);
        magic.addKunde(kunde2);
        magic.addKunde(kunde3);


        System.out.println(magic);
        System.out.println(kunde1);
        System.out.println(kunde2);
        System.out.println(kunde3);*/

        Input inputReader = new Input();
        int auswahl = -1;

        while (auswahl != 7) {
            printMenu();
            auswahl = inputReader.readInt(1, 7);
        }
    }


    private static void printMenu() {
        System.out.println("+==================================================================+");
        System.out.println("| (01) Privatkunde anlegen                                         |");
        System.out.println("| (02) Geschäftskunde anlegen                                      |");
        System.out.println("| (03) Reservierung anlegen und Kundennummer zuordnen              |");
        System.out.println("| (04) Kunde mit Reservierungen anzeigen (Auswahl durch Kundennr.) |");
        System.out.println("| (05) Kunde mit Reservierungen anzeigen (Auswahl durch Name)      |");
        System.out.println("| (06) Reservierung anzeigen (Auswahl durch Reservierungsnr.)      |");
        System.out.println("|                                                                  |");
        System.out.println("| (07) Beenden                                                     |");
        System.out.println("+==================================================================+");
        
    }

}
