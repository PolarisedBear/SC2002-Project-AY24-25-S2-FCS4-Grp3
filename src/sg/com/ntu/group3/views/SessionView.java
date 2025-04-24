package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Session;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Session View class is responsible for showing the available actions a specific user is allowed to do after logging in.
 *
 */
public class SessionView implements View{
    private static Scanner input = new Scanner(System.in);

    /** Menu showing the non-user specific capabilities
     * @param session The current session
     */
    public static void showMainMenuGlobal(Session session) {
        System.out.println("Welcome " + session.getCurrentUser().getName() + "!");
        System.out.println("Select the action you'd like to take:");
        System.out.println("1. Logout");
        System.out.println("2. Change Password");
        System.out.println("3. Update User Info");
        System.out.println("4. View User Profile");
    }

    /** Menu showing applicant-specific capabilities
     */
    public static void showMainMenuApplicant() {
        System.out.println("5. Apply for project");
        System.out.println("6. View Applied Project");
        System.out.println("7. Book Flat");
        System.out.println("8. Request Withdrawal");
        System.out.println("9. Submit Enquiry");
        System.out.println("10. View/Edit/Delete Enquiry");
    }

    /** Menu showing HDB Officer-specific capabilities
     */
    public static void showMainMenuHDBOfficer() {
        System.out.println("11. Register or Join Project");
        System.out.println("12. View Registration Status");
        System.out.println("13. View Project Details");
        System.out.println("14. Update Applicant's Flat Selection");
        System.out.println("15. View and Reply to Enquiries");
        System.out.println("16. Generate Applicant's Flat Booking Receipt");
    }

    /** Menu showing HDB Manager-specific capabilities
     */
    public static void showMainMenuHDBManager() {
        System.out.println("5. Create/edit/delete BTO Listing");
        System.out.println("6. View Projects");
        System.out.println("7. Approve/Reject BTO Application");
        System.out.println("8. Approve/Reject BTO Withdrawal");
        System.out.println("9. Generate Report");
        System.out.println("10. View Enquiries");
        System.out.println("11. Approve Officer Registration");
    }

    /** Consolidated method to show all the options available to an applicant user.
     * @param session the current session
     * @return the choice of action given by the user
     */
    public static int showMainMenuApplicant(Session session) {
        int choice = 0;
        while (choice<1 || choice>10) {
            View.lineSeparator();
            showMainMenuGlobal(session);
            showMainMenuApplicant();
            View.lineSeparator();
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
        }
        return choice;
    }

    /** Consolidated method to show all the options available to an officer user.
     * @param session the current session
     * @return the choice of action given by the user
     */
    public static int showMainMenuHDBOfficer(Session session) {
        int choice = 0;
        while (choice<1 || choice>16) {
            View.lineSeparator();
            showMainMenuGlobal(session);
            showMainMenuApplicant();
            showMainMenuHDBOfficer();
            View.lineSeparator();
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
        }
        return choice;
    }

    /** Consolidated method to show all the options available to a manager user.
     * @param session the current session
     * @return the choice of action given by the user
     */
    public static int showMainMenuHDBManager(Session session) {
        int choice = 0;
        while (choice<1 || choice>11) {
            View.lineSeparator();
            showMainMenuGlobal(session);
            showMainMenuHDBManager();
            View.lineSeparator();
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
        }
        return choice;
    }

    /** Overloaded method from the View interface to show the outcome of an operation
     * @param action The operation taken
     * @param success true if operations was successful, false otherwise.
     */
    public static void showOperationOutcome(String action, boolean success) {
        if (success) {
            System.out.println(action + " successful");
        } else {
            System.out.println(action + " unsuccessful");
        }
    }


}
