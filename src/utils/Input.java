package utils;


import models.Kunde;
import models.Reiseagentur;
import models.Reservierung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                    System.out.println(String.format("Ihre Eingabe ist nicht im richtigen Intervall %02d-%02d", min, max));
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

    public Date readDate(String msg) {
        System.out.println(msg + "(dd.MM.yyyy)");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        while (true) {
            String dateString = scanner.nextLine();
            try {
                date = simpleDateFormat.parse(dateString);
                break;
            } catch (ParseException e) {
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
}
