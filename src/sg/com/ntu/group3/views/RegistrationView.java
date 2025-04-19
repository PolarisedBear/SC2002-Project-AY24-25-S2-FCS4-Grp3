package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Registration;

import java.util.List;
import java.util.Scanner;

public class RegistrationView implements View{
    private static Scanner input = new Scanner(System.in);

    public static Registration ChoosePendingReg(List<Registration> pendingRegs) {
        System.out.println("Pending registrations");
        for (int i = 0; i < pendingRegs.size(); i++) {
            Registration pendingReg = pendingRegs.get(i);
            System.out.println(i + ". Project: " + pendingReg.getProject().getName() +
                    ", Officer: " + pendingReg.getOfficer()+
                    ", Status: " + pendingReg.getStatus());
        }
        System.out.print("Select a registration number to approve or reject: ");
        int choice = input.nextInt();
        input.nextLine();
        if (choice >= 0 && choice < pendingRegs.size()) {
            return pendingRegs.get(choice);
        } else {
            System.out.println("invalid selection.");
            return null;
        }
    };
    public static int chooseApproveReject(){
        System.out.println("Approve or reject the registration? 1. Approve 2. Reject");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            System.out.println("Registration approved.");
        } else if (choice == 2) {
            System.out.println("Registration rejected.");
        } else {
            System.out.println("Invalid selection.");
        }
        return choice;
    }

}
