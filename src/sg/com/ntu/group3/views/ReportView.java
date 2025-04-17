package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

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
    public void generateReport() {};
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
