package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**vApplication View Class responsible for displaying forms and information related to applications
 * <p>Includes methods that ask and accept user input, to be used in processing through various controllers and models.</p>
 */
public class ApplicationView implements View {
    private static Scanner input = new Scanner(System.in);


    /** Method for displaying a given list of projects, and accepting the name of one such project as input.
     * Used during project application.
     * @param projectList The list to be printed to the console
     * @return The name of the project input from the user.
     */
    public static String displayApplicationFormList(List<Project> projectList) {
        System.out.println("Currently Valid Projects");
        for (Project proj : projectList) {
            System.out.println(proj);
            View.lineSeparator();
            System.out.println("\n");
        }
        return input.nextLine();
    };

    /** Method to display a list of flats and the corresponding number of available flats.
     * Used by officers during flat selection
     * @param objectMap The map of flat types to integers, representing the flat and the number of such flats available
     * @return user input string for the type flat to be booked.
     */
    public static String displayBookingList(Map<FlatType, Integer> objectMap) {
        System.out.println(objectMap);
        System.out.println("Enter the name of the flat type to book:");
        return input.nextLine();
    }

    /** Method to display information about an application. Used when the applicant type user requests to view their profile
     * @param application The application to be printed to the console
     */
    public static void displayApplication(Application application) {
        System.out.println("Your currently applied project is:");
        System.out.println(application);
    }

    /** Method to display the status of a given application. Used by applicants for viewing application status.
     * @param application The application whose status info is to be displayed.
     */
    public static void displayApplicationStatus(Application application) {
        System.out.println("Application Status: ");
        System.out.println(application.getStatus());
        System.out.println("Project: ");
        System.out.println(application.getProject());
    };


    /** Method to show the booking form to initiate the flat selection process. Used by officers with an assigned project during flat selection.
     * @param applications The list of applications to be reviewed.
     * @return User input string indicating the choice of the application to be updated.
     */
    public static String showBookingForm(List<Application> applications) {
        System.out.println("Select an application from your assigned project to update booking");
        for (int i=0; i<applications.size(); i++) {
            System.out.println("["+i+"] "+ applications.get(i));
            View.lineSeparator();
        }
        System.out.println("Enter the number of the application to begin booking");
        return input.nextLine();
    }


    /** Method to display a list of pending applications. Used by managers during the application approval or rejection process
     * @param applications The already filtered list of pending applications to be displayed.
     * @return The user input integer indicating the index of a chosen application from the list.
     */
    public static int displayPendingApplications(List<Application> applications) {
        System.out.println("\n--- Pending Applications ---");
        for (int i = 0; i < applications.size(); i++) {
            System.out.println("[" + i + "] " + applications.get(i));
        }
        int choice = -1;
        try {
            System.out.print("Select an application to review (or -1 to cancel): ");
            choice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Application Retrieval", false);
            input.nextLine();
        }
        return choice;
    }

    /** Method to prompt the user to make a decision regarding application approval.
     * Used by managers as the final step for application approval/rejection.
     * @param application The chosen application subject to approval
     * @return The decision represented as an integer.
     */
    public static int promptApproveOrReject(Application application) {
        int decision = 0;
        System.out.println("Reviewing Application:\n" + application);
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        System.out.println("0. Cancel");
        System.out.print("Choose an action: ");
        try {
            decision = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
            View.showOperationOutcome("Application Approval", false);
        }

        return decision;
    }

}
