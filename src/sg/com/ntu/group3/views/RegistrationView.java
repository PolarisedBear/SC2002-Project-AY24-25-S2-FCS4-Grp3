package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Registration;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/** Registration View class is responsible for displaying forms and information related to registrations.
 *
 */
public class RegistrationView implements View{
    private static Scanner input = new Scanner(System.in);

    /** Form for managers to view pending registrations from officers.
     * @param pendingRegs The list of already filtered pending registrations
     * @return The index of the selected registration from the list.
     */
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

    /** Form asking the user for their choice to approve or reject a registration
     * @return The choice from the user to approve or reject the registration.
     */
    public static int chooseApproveReject(){
        int choice = 0;
        System.out.println("Approve or reject the registration? 1. Approve 2. Reject");
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            input.nextLine();
        }

        if (choice == 1) {
            System.out.println("Registration approved.");
        } else if (choice == 2) {
            System.out.println("Registration rejected.");
        }
        return choice;
    }

}
