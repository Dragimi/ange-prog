package utils;


import models.Kunde;
import models.Reiseagentur;
import models.Reservierung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int readInt(String msg, int min, int max) {
        if (msg != null && !msg.isEmpty()) {
            System.out.println(msg);
        }
        int input;
        while (true) {
            String inputString = scanner.nextLine();
            try {
                input = Integer.parseInt(inputString);
                if (input < min || input > max) {
                    System.out.println(String.format("Ihre Eingabe ist nicht im richtigen Intervall [%02d, %02d]", min, max));
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ihre Eingabe war fehlerhaft. Versuchen Sie es erneut.");
            }
        }
        return input;
    }

    public double readDouble(String msg, double min, double max) {
        if (msg != null && !msg.isEmpty()) {
            System.out.println(msg);
        }
        double input;
        while (true) {
            String inputString = scanner.nextLine();
            try {
                inputString = inputString.replace(",", ".");
                input = Double.parseDouble(inputString);
                if (input < min || input > max) {
                    System.out.println(String.format("Ihre Eingabe ist nicht im richtigen Intervall [%.4f, %.4f]", min, max));
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ihre Eingabe war fehlerhaft. Versuchen Sie es erneut.");
            }
        }
        return input;
    }

    public String read(String msg) {
        System.out.println(msg);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Ihre Eingabe ist leer. Bitte versuchen Sie es erneut.");
            } else {
                break;
            }
        }
        return input;
    }

    public LocalDate readDate(String msg) {
        System.out.println(msg + "(dd.MM.yyyy)");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;
        while (true) {
            String dateString = scanner.nextLine();
            try {
                date = LocalDate.parse(dateString, dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ihre Eingabe ist fehlerhaft. Geben Sie das Datum im richtigen Format erneut ein.");
            }
        }
        return date;
    }

    public Kunde getKundeByKundennummer(Reiseagentur reiseagentur) {
        System.out.println("Geben Sie die Kundennummer ein: ");
        String kundennummer = scanner.nextLine();

        if (!reiseagentur.getKunden().containsKey(kundennummer)) {
            System.out.println("Kunde nicht gefunden.");
            return null;
        } else {
            return reiseagentur.getKunden().get(kundennummer);
        }
    }

    public Reservierung getReservierungByReservierungsnnummer(Reiseagentur reiseagentur) {
        System.out.println("Geben Sie die Reservierungsnnummer ein: ");

        String reservierungsnummer = scanner.nextLine();

        for (Kunde k : reiseagentur.getKunden().values()) {
            if (k.getReservierungen().containsKey(reservierungsnummer)) {
                return k.getReservierungen().get(reservierungsnummer);
            }
        }
        return null;
    }

    public void close() {
        this.scanner.close();
    }
}
