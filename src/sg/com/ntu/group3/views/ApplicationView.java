package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ApplicationView implements View {
    private static Scanner input = new Scanner(System.in);

    public static void showOperationOutcome(String action, boolean successful) {
        if (successful) {
            System.out.println(action + " successful");
        } else {
            System.out.println(action + " unsuccessful");
        }
    }

    public static String displayApplicationFormList(List<Project> projectList) {
        System.out.println("Currently Valid Projects");
        for (Project proj : projectList) {
            System.out.println(proj);
            System.out.println("\n");
        }
        return input.nextLine();
    };

    public static String displayBookingList(Map<FlatType, Integer> objectMap) {
        System.out.println(objectMap);
        System.out.println("Enter the name of the flat type to book:");
        return input.nextLine();
    }

    public static void displayApplication(Application application) {
        System.out.println("Your currently applied project is:");
        System.out.println(application);
    }
    public static void displayApplicationStatus(Application application) {
        System.out.println("Application Status: ");
        System.out.println(application.getStatus());
        System.out.println("Project: ");
        System.out.println(application.getProject());
    };
    public void showWithdrawalForm() {};
    public static void showBookingForm(Application application, Applicant applicant) {
        System.out.println("Application Successful. Proceed to Flat Booking");
        System.out.println("Available Flats:");

    };
    public static String showBookingForm(List<Application> applications) {
        System.out.println("Select an application from your assigned project to update booking");
        System.out.println(applications);
        System.out.println("Enter the NRIC of the applicant to begin booking");
        return input.nextLine();
    }


    public static int displayPendingApplications(List<Application> applications) {
        System.out.println("\n--- Pending Applications ---");
        for (int i = 0; i < applications.size(); i++) {
            System.out.println("[" + i + "] " + applications.get(i));
        }
        System.out.print("Select an application to review (or -1 to cancel): ");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public static int promptApproveOrReject(Application application) {
        System.out.println("Reviewing Application:\n" + application);
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        System.out.println("0. Cancel");
        System.out.print("Choose an action: ");
        int decision = input.nextInt();
        input.nextLine();
        return decision;
    }
}
