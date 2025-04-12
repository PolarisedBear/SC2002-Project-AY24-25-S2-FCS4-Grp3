package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;

import java.util.List;
import java.util.Scanner;

public class ApplicationView {
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

    public static
    public void displayApplicationStatus() {};
    public void showWithdrawalForm() {};
    public void showBookingForm() {};
}
