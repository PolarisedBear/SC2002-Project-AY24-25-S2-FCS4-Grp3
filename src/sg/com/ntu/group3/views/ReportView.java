package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Report View class is responsible for printing manager and officer generated reports and receipts.
 * <p>Includes methods for gathering user input to assist with report and receipt generation, such as filtering and application selection.</p>
 *
 */
public class ReportView implements View{
    private static Scanner input = new Scanner(System.in);

    /** Form to show the list of application to an officer's assigned project, and prompt for an applicant's NRIC from those applications
     * @param officer The officer making the request
     * @return The nric of the selected applicant
     */
    public static String displayReceiptOptions(HDBOfficer officer) {
        System.out.println("List of applications to assigned project:\n");
        Project assignedProject = officer.getAssignedProject();
        for (Application application : assignedProject.getApplications()) {
            ApplicationView.displayApplication(application);
            View.lineSeparator();
        }
        System.out.println("Enter the nric of the applicant to generate the receipt:");
        return input.nextLine();
    };

    /** Form to gather user input for a selected project from the list of projects created by a manager.
     * @param managerProjects The list of projects created by a given manager
     * @return the project of choice, indicated by its index from the list.
     */
    public static int generateReportForm(List<Project> managerProjects) {
        System.out.println("\n--- Your Projects ---");
        for (int i = 0; i < managerProjects.size(); i++) {
            System.out.println("[" + i + "] " + managerProjects.get(i).getName());
        }

        System.out.print("Select a project to generate report for: ");
        int projectChoice = -1;
        try {
            projectChoice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Report Generation", false);
            input.nextLine();
        }

        return projectChoice;
    };

    /** Form to query the user for the number of applicants to be included in the report.
     * @return the number indicated by the user's input.
     */
    public static int reportFormApplicantQuery() {
        System.out.print("Enter number of applicants to include in the report: ");
        int count = 0;
        try {
            count = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Report Generation", false);
            input.nextLine();
        }
        return count;
    }

    /** Form to query the user for a choice of filter to apply to the applications used to generate the report
     * @return user choice
     */
    public static int reportFormFilterQuery() {
        System.out.println("Enter the filter to use:");
        System.out.println("0 (default). No Filter");
        System.out.println("1. Filter By Marital Status");
        System.out.println("2. Filter By Age");
        System.out.println("3. Filter By Flat Type");
        View.lineSeparator();
        int choice = 0;
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Filtering", false);
            input.nextLine();
        }
        return choice;
    }

    /** Form to query the user for the marital status of applicants to filter by. Used after reportFormFilterQuery.
     * @return true to filter only married applicants. False for single applicants.
     */
    public static boolean maritalStatusFilterQuery() {
        String choice = "";
        while (!choice.equalsIgnoreCase("m") && !choice.equalsIgnoreCase("s")) {
            System.out.println("Type M to filter only married applicants, and S to filter only single applicants");
            choice = input.nextLine();
            if (!choice.equalsIgnoreCase("m") && !choice.equalsIgnoreCase("s")) {
                System.out.println("Invalid Choice!");
            }
        }
        return choice.equalsIgnoreCase("m");
    }

    /** Form to query the user for the age range of applicants to filter by. Used after reportFormFilterQuery.
     * @return a map entry storing the minimum age and maximum age to filter by.
     */
    public static Map.Entry<Integer, Integer> ageFilterQuery() {
        int lowerBound = 0;
        int upperBound = 0;
        while (lowerBound<=0 || upperBound<=0 || lowerBound>upperBound) {
            System.out.println("Enter the age range to filter by");
            try {
                System.out.println("Enter lower bound:");
                lowerBound = input.nextInt();
                input.nextLine();
                System.out.println("Enter upper bound:");
                upperBound = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                input.nextLine();
            }
        }
        return Map.entry(lowerBound, upperBound);
    }

    /** Form to query the user for the flat type to filter by. Used after reportFormFilterQuery.
     * @return a string input from the user representing the name of the flat type to filter by.
     */
    public static String flatTypeFilterQuery() {
        String choice = "";
        while (!FlatType.doesFlatTypeExist(choice)) {
            System.out.println("Enter the flat type to filter by");
            System.out.println("Valid Types: " + FlatType.getTypeList().keySet());
            choice = input.nextLine();
        }
        return choice;
    }

    /** Method to print the full details of an application receipt.
     * @param details A map of all the details of an application to print.
     */
    public static void displayReceipt(Map<String, String> details) {
        System.out.println("Application Receipt");
        View.lineSeparator();
        System.out.println("Applicant's Name: " + details.get("name"));
        System.out.println("Applicant's NRIC: " + details.get("nric"));
        System.out.println("Applicant's Age: " + details.get("age"));
        System.out.println("Applicant's Marital Status: " + details.get("maritalStatus"));
        System.out.println("Applied Project: " + details.get("projName"));
        System.out.println("Application Period " + details.get("projOpenDate") + " - " + details.get("projCloseDate"));
        System.out.println("Booking Status: " + details.get("bookingStatus"));
        System.out.println("Flat Type Booked: " + details.get("bookedFlat"));
        View.lineSeparator();

    };
}
