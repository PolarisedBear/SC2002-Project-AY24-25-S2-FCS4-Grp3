package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.Applicant;

import java.util.InputMismatchException;
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


    public static int selectWithdrawalRequest(List<WithdrawalRequest> requests) {
        System.out.println("\n--- Pending Withdrawal Requests ---");
        for (int i = 0; i < requests.size(); i++) {
            System.out.println("[" + i + "] Application: " + requests.get(i).getApplication());
        }
        int inputNum = -1;
        try {
            System.out.print("Select a request to review (or -1 to cancel): ");
            inputNum = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Withdrawal Selection", false);
            input.nextLine();
        }

        return inputNum;
    }

    public static int promptApproveOrReject(WithdrawalRequest request) {
        System.out.println("\nReviewing Withdrawal Request:");
        System.out.println("Application:\n" + request.getApplication());
        System.out.println("1. Approve");
        System.out.println("2. Reject");
        System.out.println("0. Cancel");
        System.out.print("Choose an action: ");
        int decision = 0;
        try {
            decision = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Operation", false);
            input.nextLine();
        }

        return decision;
    }
}
