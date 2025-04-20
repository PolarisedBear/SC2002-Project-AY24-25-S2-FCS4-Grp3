package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportView implements View{
    private static Scanner input = new Scanner(System.in);

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

    public static String flatTypeFilterQuery() {
        String choice = "";
        while (!FlatType.doesFlatTypeExist(choice)) {
            System.out.println("Enter the flat type to filter by");
            System.out.println("Valid Types: " + FlatType.getTypeList().keySet());
            choice = input.nextLine();
        }
        return choice;
    }

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
