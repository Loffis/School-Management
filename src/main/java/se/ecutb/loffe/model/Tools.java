package se.ecutb.loffe.model;

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
                //System.out.println("Please enter a number.");
                return number;
            }
        }
        return number;
    }
}
