import models.*;
import utils.Input;
import utils.Utils;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;


public class Main {

    public static void main(String[] args) {
        Reiseagentur magic = new Reiseagentur(
                "Magic Holidays Reiseagentur",
                "DE812524001",
                new Adresse("Hauptstraße", "5a", 10559, "Berlin")
        );

        generateDumpDate(magic);

        Input inputReader = new Input();
        int auswahl = -1;

        while (auswahl != 7) {
            printMenu();
            auswahl = inputReader.readInt(null, 0, 7);

            switch (auswahl) {
                case 0: {
                    Kunde.printShortTableHeader();
                    for (Kunde k : magic.getKunden().values()) {
                        Kunde.printShortTableRow(k);
                    }
                    break;
                }
                case 1: {
                    System.out.println("Füllen Sie das folgende Formular aus: ");
                    Privatkunde kunde = Privatkunde.readPrivatKundeFromInput(inputReader);
                    magic.addKunde(kunde);
                    System.out.println("Privatkunde wurde erfolgreich erstellt.");
                    break;
                }
                case 2: {
                    System.out.println("Füllen Sie das folgende Formular aus: ");
                    Geschaeftskunde kunde = Geschaeftskunde.readGeschaeftsKundeFromInput(inputReader);
                    magic.addKunde(kunde);
                    System.out.println("Geschäftskunde wurde erfolgreich erstellt.");
                    break;
                }
                case 3: {
                    int reservierungsart = inputReader.readInt("0: Flug oder 1: Hotel  -  Reservierung", 0, 1);
                    Reservierung reservierung;
                    if (reservierungsart == 0) {
                        reservierung = Flugreservierung.readFlugreservierungFromInput(inputReader);
                    } else {
                        reservierung = Hotelreservierung.readHotelreservierungFromInput(inputReader);
                    }
                    Kunde kunde = inputReader.getKundeByKundennummer(magic);
                    kunde.addReservierung(reservierung);
                    System.out.println("Reservierung wurde erfolgreich erstellt und dem Kunden mit der Nummer '" + kunde.getKundenNummer() + "' zugeordnet");
                    break;
                }
                case 4: {
                    Kunde k = inputReader.getKundeByKundennummer(magic);
                    if (k != null) {
                        Kunde.printLongTableHeader();
                        Kunde.printLongTableRow(k);
                        System.out.println();

                        if (!k.getReservierungen().isEmpty()) {
                            Reservierung.printLongTableHeader();
                            for (Reservierung r : k.getReservierungen().values()) {
                                Reservierung.printLongTableRow(r);
                            }
                        }
                    }
                    System.out.println();
                    break;
                }
                case 5: {
                    String name = inputReader.read("Geben Sie den Namen (Vorname Nachname) ein: ");
                    Set<Kunde> filteredList = magic.getKunden().values().stream().filter((it) -> it.getName().trim().equalsIgnoreCase(name.trim())).collect(Collectors.toSet());
                    if (filteredList.isEmpty()) {
                        System.out.println("Keinen Kunden mit dem eingegeben Namen gefunden.");
                    }
                    else {
                        Kunde.printLongTableHeader();
                        for(Kunde k: filteredList) {
                            Kunde.printLongTableRow(k);

                            if (!k.getReservierungen().isEmpty()) {
                                System.out.println();
                                Reservierung.printLongTableHeader();
                                for (Reservierung r : k.getReservierungen().values()) {
                                    Reservierung.printLongTableRow(r);
                                }
                            }
                        }
                    }
                    System.out.println();
                    break;
                }
                case 6: {
                    Reservierung r = inputReader.getReservierungByReservierungsnnummer(magic);
                    if (r != null) {
                        Reservierung.printLongTableHeader();
                        Reservierung.printLongTableRow(r);
                    }
                    else {
                        System.out.println("Keine Reservierung mit der eingegebenen Nummer gefunden.");
                    }
                    break;
                }
                case 7: {
                    System.out.println();
                    System.out.println("Auf Wiedersehen.");
                    break;
                }
                default:
                    System.out.println("Noch nicht implementiert.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("+==================================================================+");
        System.out.println("| (00) Alle Kunden anzeigen                                        |");
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

    private static void generateDumpDate(Reiseagentur magic) {

        Kunde kunde1 = new Privatkunde("Hr.",
                "Max",
                "Mustermann",
                Utils.strToDate("02.10.1992"),
                new Adresse("Julius-Leber-Str.", "234c", 38531, "Hamburg"),
                "0176 123 452 12",
                "max.mustermann@gmail.com");
        kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.PAYPAL, "Konto: " + kunde1.getEmail()));
        kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4235 7453 1234 7456"));
        kunde1.addReservierung(new Hotelreservierung(Utils.strToDate("29.11.2019"), 403.35, "Estrel Hotel Berlin", Duration.of(2, DAYS)));


        Kunde kunde2 = new Privatkunde("Fr.",
                "Melanie",
                "Mustermann",
                Utils.strToDate("14.08.1994"),
                new Adresse("Ernst-Ruska-Ufer", "14", 40124, "München"),
                "0163 646 123 24",
                "m.m@yahoo.de");
        kunde2.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4438 8123 9735 2345"));
        kunde2.addReservierung(new Flugreservierung(Utils.strToDate("12.05.2020"), 120.53, "TL1923", "IST - Istanbul", "TXL - Tefel Berlin"));
        kunde2.addReservierung(new Hotelreservierung(Utils.strToDate("12.05.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(1, DAYS)));

        Kunde kunde3 = new Privatkunde("Hr.",
                "Dragana",
                "Mitrovic",
                Utils.strToDate("27.11.1993"),
                new Adresse("Olgastr.", "24c", 10582, "Berlin"),
                "0176 123 452 12",
                "d.m@gmail.com");
        kunde3.addReservierung(new Flugreservierung(Utils.strToDate("17.06.2020"), 120.53, "L234", "Köln", "TXL - Tefel Berlin"));
        kunde3.addReservierung(new Hotelreservierung(Utils.strToDate("17.06.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(3, DAYS)));
        kunde3.addReservierung(new Flugreservierung(Utils.strToDate("21.06.2020"), 120.53, "L523", "TXL - Tefel Berlin", "Köln"));

        Kunde kunde4 = new Geschaeftskunde("Prof. Dr.",
                "Andreas",
                "Müller",
                Utils.strToDate("12.06.1980"),
                new Adresse("Straße des 17. Juni", "135", 10942, "Berlin"),
                "0177 123 342 12",
                "andreas.mueller@spotify.com",
                "Spotify Inc.");
        kunde4.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Email"));

        Kunde kunde5 = new Geschaeftskunde("Hr.",
                "Peter",
                "Schulz",
                Utils.strToDate("12.06.1960"),
                new Adresse("Audistr.", "1", 24725, "Ingolstadt"),
                "0178 823582384",
                "p.schulz@audi.de",
                "Audi GmbH");
        kunde5.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Email"));

        Kunde kunde6 = new Geschaeftskunde("Fr.",
                "Simone",
                "Meier",
                Utils.strToDate("02.04.1968"),
                new Adresse("Heinrich-Heine-Str.", "13c", 88623, "Friedrichshafen"),
                "0178 872368235",
                "simone.meier@zf.de",
                "ZF Friedrichshafen AG");
        kunde5.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Post"));

        magic.addKunde(kunde1);
        magic.addKunde(kunde2);
        magic.addKunde(kunde3);
        magic.addKunde(kunde4);
        magic.addKunde(kunde5);
        magic.addKunde(kunde6);
    }
}
