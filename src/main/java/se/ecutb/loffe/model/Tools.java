package se.ecutb.loffe.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Tools {

    private static Scanner SCANNER = new Scanner(System.in);

    public static String getString(){
        return SCANNER.nextLine();
    }

    public static int getValidInt() {
        boolean valid = false;
        int number = 0;
        while (!valid) {
            try {
                number = Integer.parseInt(getString());
                valid = true;
            } catch (NumberFormatException exception) {
                return number;
            }
        }
        return number;
    }

    public static LocalDate getValidDate() {
        boolean valid = false;
        LocalDate date = LocalDate.parse("1900-01-01");
        while (!valid) {
            try {
                date = LocalDate.parse(Tools.getString());
                valid = true;
            } catch (DateTimeException e) {
                System.out.println("\nNot a valid input. Date default set to 1900-01-01.");
                return date;
            }
        }
        return date;
    }
}
