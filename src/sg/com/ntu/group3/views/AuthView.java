package sg.com.ntu.group3.views;

import java.util.Scanner;

/** AuthView class is responsible for displaying and collecting user input before login.
 *
 */
public class AuthView implements View{
    private static Scanner input = new Scanner(System.in);

    /** Method to collect user input for login
     * @return the input string as NRIC to be verified
     */
    public static String showLoginScreenNRIC() {
        System.out.println("Enter your NRIC:");
        return input.nextLine();
    }

    /** Method to collect user input for the password
     * @return the password string to be verified
     */
    public static String showLoginScreenPassword() {
        System.out.println("Enter your password:");
        return input.nextLine();
    }

    /** Default welcome screen before logging in.
     * @return user input to login or to quit and exit th program.
     */
    public static int welcomeScreen() {
        int choice = 2;
        while (choice!=1 && choice!=0) {
            View.lineSeparator();
            System.out.println("Welcome to BTOManager!");
            System.out.println("Enter your choice:");
            System.out.println("1. Login");
            System.out.println("0. Quit");
            View.lineSeparator();
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }


}
