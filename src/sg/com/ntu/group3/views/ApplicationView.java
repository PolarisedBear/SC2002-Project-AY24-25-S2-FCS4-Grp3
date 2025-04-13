package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;

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
        System.out.println(projectList);
        return input.nextLine();
    };

    public static String displayBookingList(Map<FlatType, Integer> objectMap) {
        System.out.println(objectMap);
        System.out.println("Enter the name of the flat type to book:");
        return input.nextLine();
    }

    public static void displayApplication(Application application) {
        System.out.println(application);
    }
    public void displayApplicationStatus() {};
    public void showWithdrawalForm() {};
    public static void showBookingForm(Application application, Applicant applicant) {
        System.out.println("Application Successful. Proceed to Flat Booking");
        System.out.println("Available Flats:");

    };
}
