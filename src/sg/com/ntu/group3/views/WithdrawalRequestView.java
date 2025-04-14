package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.Applicant;

import java.util.List;
import java.util.Scanner;

public class WithdrawalRequestView implements View{
    private static Scanner input = new Scanner(System.in);

    public WithdrawalRequestView() {

    }

    public static void displayWithdrawalRequest(Application application) {};
    public static void displayPendingRequests(List<WithdrawalRequest> requestList) {};
    public static void showRequestStatus(WithdrawalRequest request) {};
    public static String showWithdrawalConfirmation(Applicant applicant) {
        ApplicationView.displayApplication(applicant.getApplication());
        System.out.println("Are you sure you want to withdraw? Y/N");
        return input.nextLine();
    }

    public static void showOperationOutcomes(boolean success) {
        if (success) System.out.println("Request Successful");
        else System.out.println("Request Unsuccessful");
    }
}
