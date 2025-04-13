package sg.com.ntu.group3.views;

import sg.com.ntu.group3.controllers.Session;
import sg.com.ntu.group3.roles.Applicant;

import java.util.Scanner;

public class SessionView implements View{
    private static Scanner input = new Scanner(System.in);

    public static void showMainMenuGlobal(Session session) {
        System.out.println("Welcome " + session.getCurrentUser().getName() + "!");
        System.out.println("Select the action you'd like to take:");
        System.out.println("1. Logout");
        System.out.println("2. Change Password");
        System.out.println("3. Update User Info");
        System.out.println("4. View User Profile");
    }

    public static void showMainMenuApplicant() {
        System.out.println("5. Apply for project");
        System.out.println("6. View Applied Project");
        System.out.println("7. Book Flat");
        System.out.println("8. Request Withdrawal");
        System.out.println("9. Submit Enquiry");
        System.out.println("10. View/Edit/Delete Enquiry");
    }

    public static void showMainMenuHDBOfficer() {
        System.out.println("11. Register or Join Project");
        System.out.println("12. View Registration Status");
        System.out.println("13. View Project Details");
        System.out.println("14. Update Applicant's Flat Selection");
        System.out.println("15. View and Reply to Enquiries");
        System.out.println("16. Generate Applicant's Flat Booking Receipt");
    }

    public static void showMainMenuHDBManager() {
        System.out.println("5. Create/edit/delete BTO Listing");
        System.out.println("6. View Projects");
        System.out.println("7. Approve/Reject BTO Application");
        System.out.println("8. Approve/Reject BTO Withdrawal");
        System.out.println("9. Generate Report");
        System.out.println("10. View Enquiries");
    }

    public static int showMainMenuApplicant(Session session) {
        int choice = 0;
        while (choice<1 || choice>10) {
            showMainMenuGlobal(session);
            showMainMenuApplicant();
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }

    public static int showMainMenuHDBOfficer(Session session) {
        int choice = 0;
        while (choice<1 || choice>16) {
            showMainMenuGlobal(session);
            showMainMenuApplicant();
            showMainMenuHDBOfficer();
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }

    public static int showMainMenuHDBManager(Session session) {
        int choice = 0;
        while (choice<1 || choice>10) {
            showMainMenuGlobal(session);
            showMainMenuHDBManager();
            choice = input.nextInt();
            input.nextLine();
        }
        return choice;
    }

    public static void showOperationOutcome(String action, boolean success) {
        if (success) {
            System.out.println(action + " successful");
        } else {
            System.out.println(action + " unsuccessful");
        }
    }


}
