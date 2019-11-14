package utils;

import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public int readInt(int min, int max) {
        int input;
        while(true) {
            String inputString = scanner.nextLine();
            try {
                input = Integer.parseInt(inputString);
                if (input < min || input > max) {
                    System.out.println(String.format("Ihre Eingabe ist nicht im richtigen Intervall %02d-%02d", min, max));
                }
                else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ihre Eingabe war fehlerhaft. Versuchen Sie es erneut.");
            }
        }
        return input;
    }
}
