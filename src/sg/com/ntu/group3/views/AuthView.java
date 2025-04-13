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

    public static int welcomeScreen() {
        int choice = 2;
        while (choice!=1 || choice!=0) {
            System.out.println("Welcome to BTOManager!");
            System.out.println("Enter your choice:");
            System.out.println("1. Login");
            System.out.println("0. Quit");
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }


}
