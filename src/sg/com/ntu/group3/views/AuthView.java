package sg.com.ntu.group3.views;

import java.util.Scanner;

public class AuthView {
    private static Scanner input = new Scanner(System.in);

    public static String showLoginScreenNRIC() {
        System.out.println("Enter your NRIC:");
        return input.nextLine();
    }

    public static String showLoginScreenPassword() {
        System.out.println("Enter your password:");
        return input.nextLine();
    }
}
