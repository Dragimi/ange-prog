import buchhaltung.Zahlung;
import models.*;
import utils.Input;
import utils.Utils;

import java.io.*;
import java.nio.file.FileSystems;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Main {

    private static Reiseagentur magic;
    private static Input inputReader = new Input();

    public static void main(String[] args) {
        Reiseagentur magic = null;

        generateDumpDate();
        int auswahl = -1;

        while (auswahl != 16) {
            printMenu();
            auswahl = inputReader.readInt(null, 1, 16);

            processUserInput(auswahl);
        }
    }

    /**
     *
     */
    private static void generateDumpDate() {
        magic = new Reiseagentur(
                "Magic Holidays Reiseagentur",
                "DE812524001",
                new Adresse("Hauptstraße", "5a", 10559, "Berlin")
        );
        magic.initObserver();

        Kunde kunde1 = new Privatkunde("Hr.",
                "Max",
                "Mustermann",
                Utils.strToDate("02.10.1992"),
                new Adresse("Julius-Leber-Str.", "234c", 38531, "Hamburg"),
                "0176 123 452 12",
                "max.mustermann@gmail.com");
        // kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.PAYPAL, "Konto: " + kunde1.getEmail()));
        // kunde1.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4235 7453 1234 7456"));
        kunde1.addReservierung(new Hotelreservierung(Utils.strToDate("12.05.2020"), 403.35, "Estrel Hotel Berlin", Duration.of(2, DAYS)));


        Kunde kunde2 = new Privatkunde("Fr.",
                "Melanie",
                "Meier",
                Utils.strToDate("14.08.1994"),
                new Adresse("Ernst-Ruska-Ufer", "14", 40124, "München"),
                "0163 646 123 24",
                "m.m@yahoo.de");
        // kunde2.addZahlungsmethode(new Bezahlmethode(PaymentType.VISA, "4438 8123 9735 2345"));
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
        kunde3.addReservierung(new Flugreservierung(Utils.strToDate("12.05.2020"), 120.53, "L523", "TXL - Tefel Berlin", "Köln"));

        Kunde kunde4 = new Geschaeftskunde("Prof. Dr.",
                "Andreas",
                "Müller",
                Utils.strToDate("12.06.1980"),
                new Adresse("Straße des 17. Juni", "135", 10942, "Berlin"),
                "0177 123 342 12",
                "andreas.mueller@spotify.com",
                "Spotify Inc.");
        // kunde4.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Email"));
        kunde4.addReservierung(new Hotelreservierung(Utils.strToDate("17.06.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(3, DAYS)));

        Kunde kunde5 = new Geschaeftskunde("Hr.",
                "Peter",
                "Schulz",
                Utils.strToDate("12.06.1960"),
                new Adresse("Audistr.", "1", 24725, "Ingolstadt"),
                "0178 823582384",
                "p.schulz@audi.de",
                "Audi GmbH");
        // kunde5.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Email"));
        kunde5.addReservierung(new Hotelreservierung(Utils.strToDate("31.03.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(3, DAYS)));
        kunde5.addReservierung(new Flugreservierung(Utils.strToDate("12.05.2020"), 120.53, "L523", "TXL - Tegel Berlin", "Köln"));

        Kunde kunde6 = new Geschaeftskunde("Fr.",
                "Simone",
                "Meier",
                Utils.strToDate("02.04.1968"),
                new Adresse("Heinrich-Heine-Str.", "13c", 88623, "Friedrichshafen"),
                "0178 872368235",
                "simone.meier@zf.de",
                "ZF Friedrichshafen AG");
        // kunde6.addZahlungsmethode(new Bezahlmethode(PaymentType.RECHNUNG, "Post"));
        kunde6.addReservierung(new Hotelreservierung(Utils.strToDate("31.03.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(3, DAYS)));
        kunde6.addReservierung(new Flugreservierung(Utils.strToDate("12.05.2020"), 120.53, "L523", "TXL - Tegel Berlin", "Köln"));
        kunde6.addReservierung(new Hotelreservierung(Utils.strToDate("10.01.2020"), 5334.12, "Hotel Adlon Berlin", Duration.of(3, DAYS)));
        kunde6.addReservierung(new Hotelreservierung(Utils.strToDate("17.06.2020"), 534.12, "Hotel Adlon Berlin", Duration.of(6, DAYS)));

        magic.addKunde(kunde1);
        magic.addKunde(kunde2);
        magic.addKunde(kunde3);
        magic.addKunde(kunde4);
        magic.addKunde(kunde5);
        magic.addKunde(kunde6);
    }

    /**
     * Gibt das Menu aus
     */
    private static void printMenu() {
        System.out.println("+==================================================================+");
        System.out.println("| (01) Privatkunde anlegen                                         |");
        System.out.println("| (02) Geschäftskunde anlegen                                      |");
        System.out.println("| (03) Reservierung anlegen und Kundennummer zuordnen              |");
        System.out.println("| (04) Kunde mit Reservierungen anzeigen (Auswahl durch Kundennr.) |");
        System.out.println("| (05) Kunde mit Reservierungen anzeigen (Auswahl durch Name)      |");
        System.out.println("| (06) Reservierung anzeigen (Auswahl durch Reservierungsnr.)      |");
        System.out.println("| (07) Alle Kunden sortiert nach Namen anzeigen                    |");
        System.out.println("| (08) Übersicht aller Bezahlmethoden                              |");
        System.out.println("| (09) Alle Reservierungen eines Datums (sortiert nach Nachnamen)  |");
        System.out.println("| (10) Daten Export                                                |");
        System.out.println("| (11) Daten Import                                                |");
        System.out.println("| (12) Kunden nach Namen sortiert als CSV-Datei exportieren        |");
        System.out.println("| (13) Reservierung Checkout                                       |");
        System.out.println("| (14) Buchhaltungsliste zeigen                                    |");
        System.out.println("| (15) Auditingliste zeigen                                        |");
        System.out.println("|                                                                  |");
        System.out.println("| (16) Beenden                                                     |");
        System.out.println("+==================================================================+");
    }

    /**
     * Führt weitere Anweisungen entsprechend der Usereingabe aus.
     *
     * @param auswahl
     */
    private static void processUserInput(int auswahl) {
        if (magic == null && auswahl != 11) {
            System.out.println("Reiseagentur noch nicht definiert. Bitte importieren Sie zunächst eine Agentur mit Hilfe Menueintrag 11.");
            return;
        }
        switch (auswahl) {
            case 1: {
                privatKundeAnlegen();
                break;
            }
            case 2: {
                geschaeftsKundeAnlegen();
                break;
            }
            case 3: {
                reservierungAnlegen();
                break;
            }
            case 4: {
                kundenDurchNummerSuchen();
                break;
            }
            case 5: {
                kundenDurchNameSuchen();
                break;
            }
            case 6: {
                reservierungSuchen();
                break;
            }
            case 7: {
                kundenSortiertAnzeigen();
                break;
            }
            case 8: {
                bezahlmethodenAnzeigen();
                break;
            }
            case 9: {
                reservierungenSortiertAnzeigen();
                break;
            }
            case 10: {
                datenExport();
                break;
            }
            case 11: {
                datenImport();
                break;
            }
            case 12: {
                kundenSortiertCSVExport();
                break;
            }
            case 13: {
                reservierungCheckout();
                break;
            }
            case 14: {
                zeigeBuchhaltungsListe();
                break;
            }
            case 15: {
                zeigeAuditingListe();
                break;
            }
            case 16: {
                beenden();
                break;
            }
            default:
                System.out.println("Noch nicht implementiert.");
        }
    }

    // CASES

    /**
     * 01
     */
    private static void privatKundeAnlegen() {
        System.out.println("Füllen Sie das folgende Formular aus: ");
        Privatkunde kunde = Privatkunde.readPrivatKundeFromInput(inputReader);
        magic.addKunde(kunde);
        System.out.println("Privatkunde wurde erfolgreich erstellt.");
    }

    /**
     * 02
     */
    private static void geschaeftsKundeAnlegen() {
        System.out.println("Füllen Sie das folgende Formular aus: ");
        Geschaeftskunde kunde = Geschaeftskunde.readGeschaeftsKundeFromInput(inputReader);
        magic.addKunde(kunde);
        System.out.println("Geschäftskunde wurde erfolgreich erstellt.");
    }

    /**
     * 03
     */
    private static void reservierungAnlegen() {
        int reservierungsart = inputReader.readInt("0: Flug oder 1: Hotel  -  Reservierung", 0, 1);
        Reservierung reservierung;
        if (reservierungsart == 0) {
            reservierung = Flugreservierung.readFlugreservierungFromInput(inputReader);
        } else {
            reservierung = Hotelreservierung.readHotelreservierungFromInput(inputReader);
        }
        Kunde kunde = inputReader.getKundeByKundennummer(magic);
        if (kunde == null) {
            return;
        }
        else {
            kunde.addReservierung(reservierung);
            System.out.println("Reservierung wurde erfolgreich erstellt und dem Kunden mit der Nummer '" + kunde.getKundenNummer() + "' zugeordnet");
        }
    }

    /**
     * 04
     */
    private static void kundenDurchNummerSuchen() {
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
    }

    /**
     * 05
     */
    private static void kundenDurchNameSuchen() {
        String name = inputReader.read("Geben Sie den Namen (Vorname Nachname) ein: ");
        Set<Kunde> filteredList = magic.getKunden()
                .values()
                .stream()
                .filter((it) -> it.getName().trim().equalsIgnoreCase(name.trim()))
                .collect(Collectors.toSet());
        if (filteredList.isEmpty()) {
            System.out.println("Keinen Kunden mit dem eingegeben Namen gefunden.");
        } else {
            Kunde.printLongTableHeader();
            for (Kunde k : filteredList) {
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
    }

    /**
     * 06
     */
    private static void reservierungSuchen() {
        Reservierung r = inputReader.getReservierungByReservierungsnnummer(magic);
        if (r != null) {
            Reservierung.printLongTableHeader();
            Reservierung.printLongTableRow(r);
        } else {
            System.out.println("Keine Reservierung mit der eingegebenen Nummer gefunden.");
        }
    }

    /**
     * 07
     */
    private static void kundenSortiertAnzeigen() {
        // sortieren der Kunden
        List<Kunde> sortedList = new ArrayList<>(magic.getKunden().values()); // noch nicht sortiert
        Collections.sort(sortedList); // sortiert

        Kunde.printShortTableHeader();
        for (Kunde k : sortedList) {
            Kunde.printShortTableRow(k);
        }
    }

    /**
     * 08
     */
    private static void bezahlmethodenAnzeigen() {
        // bezahlmethoden
        HashMap<PaymentType, Integer> payments = new HashMap<>();
        for (PaymentType p : PaymentType.values()) {
            payments.put(p, 0);
        }

        for (Kunde k : magic.getKunden().values()) {
            /*for (Bezahlmethode b : k.getBezahlmethoden()) {
                payments.put(b.getBezeichnung(), payments.get(b.getBezeichnung()) + 1);
            }*/
        }

        Map<PaymentType, Integer> sortedPayments = Utils.sortMapByValues(payments);

        for (Map.Entry<PaymentType, Integer> e : sortedPayments.entrySet()) {
            System.out.printf("%10s : %3s\n", e.getKey(), e.getValue());
        }
    }

    /**
     * 09
     */
    private static void reservierungenSortiertAnzeigen() {
        // Alle reservierungen von einem Datum sortiert nach nachnamen
        LocalDate date = inputReader.readDate("Von welchem Datum sind Reservierungen gesucht?");

        List<Kunde> sortedKunden = magic.getKunden().values()
                .stream()
                .sorted(Comparator.comparing(Kunde::getNachname))
                .collect(Collectors.toList());

        for (Kunde k : sortedKunden) {
            for (Reservierung r : k.getReservierungen().values()) {
                if (r.getDatum().equals(date)) {
                    System.out.println(String.format("%30s: %s", k.getNachname(), r.toString()));
                }
            }
        }
    }

    /**
     * 10
     */
    private static void datenExport() {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try {
            String path = inputReader.read("Geben Sie einen Pfad ein: ('./' für aktuellen Standard-Ordner)");

            if (path.trim().equalsIgnoreCase("./")) {
                path = FileSystems.getDefault().getPath("resources").toAbsolutePath().toString() + "/";
            }
            if (!path.endsWith("/")) {
                path += "/";
            }

            String filename = inputReader.read("Geben Sie einen Dateinamen ohne Endung ein: ") + ".ser";

            fout = new FileOutputStream(path + filename, false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(magic);

            System.out.println("Reiseagentur erfolgreich nach '" + path + filename + "' exportiert.");
        } catch (Exception e) {
            System.out.println("Es ist ein Fehler beim Exportieren aufgetreten. Versuchen Sie es später erneut.");
            System.out.println(e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * 11
     */
    private static void datenImport() {
        ObjectInputStream ois = null;
        FileInputStream fin = null;
        try {
            String path = inputReader.read("Geben Sie einen Pfad ein: ('./' für den aktuellen Standard-Ordner)");
            if (path.trim().equalsIgnoreCase("./")) {
                path = FileSystems.getDefault().getPath("resources").toAbsolutePath().toString() + "/";
            }
            if (!path.endsWith("/")) {
                path += "/";
            }

            File[] list = new File(path).listFiles(pathname ->
                    pathname.getAbsolutePath().endsWith(".ser")
            );

            if (list.length == 0) {
                System.out.println("Der angegebene Ordner beinhaltet keine .ser-Datei. Versuchen Sie einen anderen Ordner.");
                return;
            } else {
                System.out.println("Verfügbare Dateien: ");
                for (File f : list) {
                    System.out.println("--> " + f.getName());
                }
            }

            String filename = inputReader.read("Geben Sie den zu importierenden Dateinamen mit Endung ein: ");

            fin = new FileInputStream(path + filename);
            ois = new ObjectInputStream(fin);
            magic = (Reiseagentur) ois.readObject();
            magic.initObserver();
            System.out.println("Reiseagentur erfolgreich geladen.");
        } catch (FileNotFoundException ex) {
            System.out.println("Die angegeben Datei konnte nicht gefunden werden. Versuchen Sie es später erneut.");
        } catch (Exception e) {
            System.out.println("Es ist ein Fehler beim Importieren aufgetreten. Versuchen Sie es später erneut.");
            System.out.println(e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * 12
     */
    private static void kundenSortiertCSVExport() {
        String path = inputReader.read("Geben Sie einen Pfad ein: ('./' für aktuellen Standard-Ordner)");

        if (path.trim().equalsIgnoreCase("./")) {
            path = FileSystems.getDefault().getPath("resources").toAbsolutePath().toString() + "/";
        }
        if (!path.endsWith("/")) {
            path += "/";
        }
        String filename = inputReader.read("Geben Sie einen Dateinamen ohne Endung ein: ") + ".csv";

                // sortieren der Kunden
        List<Kunde> sortedList = new ArrayList<>(magic.getKunden().values()); // noch nicht sortiert
        Collections.sort(sortedList); // sortiert



        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(path + filename);

            csvWriter.append("Name,Anzahl Reservierungen,Email");

            for(Kunde k: sortedList) {
                csvWriter.append(String.format("\n%s,%d,%s", k.getName(), k.getReservierungen().size(), k.getEmail()));
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.printf("--> %d Datensätze wurden erfolgreich in die Datei '%s' erportiert.\n", sortedList.size(), path+filename);
        } catch (IOException e) {
            System.out.println("Es ist ein Problem beim Schreiben der .csv Datei aufgetreten. Versuchen Sie es später erneut.");
        }
    }

    /**
     * 13
     */
    private static void reservierungCheckout() {
        Reservierung reservierung = inputReader.getReservierungByReservierungsnnummer(magic);
        if (reservierung == null) {
            System.out.println("Keine Reservierung gefunden.");
            return;
        }
        System.out.printf("Wie soll die Summe %.2f Euro der Reservierung '%s' bezahlt werden?\n", reservierung.getSumme(), reservierung.getReservierungsNr());
        PaymentType[] paymentTypes = PaymentType.values();
        for (int i = 0; i < paymentTypes.length; i++) {
            System.out.printf("\t%d: %s\n", (i+1), paymentTypes[i]);
        }
        int auswahl = inputReader.readInt("", 1, paymentTypes.length + 1) - 1;
        magic.getKasse().setBezahlmethode(new Bezahlmethode(paymentTypes[auswahl]," -- zusätzliche Information -- "));
        magic.getKasse().bezahlen(reservierung.getSumme());
    }

    /**
     * 14
     */
    private static void zeigeBuchhaltungsListe() {
        int size = magic.getBuchhaltung().getZahlungen().size();
        System.out.printf("Es befinden sich derzeit %d Einträge in der Buchhaltungsliste:\n", size);
        if (size > 0) {
            for (Zahlung z: magic.getBuchhaltung().getZahlungen()) {
                System.out.printf("\t%8.2f (%s)\n", z.getBetrag(), z.getBezahlmethode().getBezeichnung());
            }
        }
    }

    /**
     * 15
     */
    private static void zeigeAuditingListe() {
        int size = magic.getAuditing().getZahlungen().size();
        System.out.printf("Es befinden sich derzeit %d Einträge in der Auditingliste:\n", size);
        if (size > 0) {
            for (Zahlung z: magic.getAuditing().getZahlungen()) {
                System.out.printf("\t%8.2f (%s)\n", z.getBetrag(), z.getBezahlmethode().getBezeichnung());
            }
        }
    }

    /**
     * 16
     */
    private static void beenden() {
        inputReader.close();
        System.out.println();
        System.out.println("Auf Wiedersehen.");
    }
}
