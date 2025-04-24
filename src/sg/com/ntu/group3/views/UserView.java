package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/** User View class is responsible for showing user information and forms regarding operations to modify user profile details
 *
 */
public class UserView {
    private static Scanner input = new Scanner(System.in);

    /** Main Form for the user to indicate their choice to update personal information
     * @return The user choice for which particulars they wish to update
     */
    public static Map.Entry<String, String> updateUserInfoForm() {
        int choice = toUpdateInfoForm();
        switch (choice) {
            case 0: break;
            case 1: return showNameChangeForm();
            case 2: return showAgeChangeForm();
            case 3: return showMaritalStatusChangeForm();
        }
        return Map.entry("", "");


    };

    /** Form to prompt the user to change their password
     * @return map entry storing the old password and new password for verification
     */
    public static Map.Entry<String, String> showPasswordChangeForm() {
        System.out.println("Changing password:");
        System.out.println("Enter old password:");
        String oldpw = input.nextLine();
        System.out.println("Enter new password:");
        String newpw = input.nextLine();
        return Map.entry(oldpw, newpw);
    };

    /** Form to display the result of a password change operation
     * @param successful true if the password was changed successfully, false if otherwise.
     */
    public static void passwordChangeResult(boolean successful) {
        if (successful) {System.out.println("Password successfully changed");}
        else {System.out.println("Password not changed");}
    }

    /** Form to prompt the user for a new name to update in their profile.
     * @return map entry storing the new name entered.
     */
    protected static Map.Entry<String, String> showNameChangeForm() {
        System.out.println("Enter new Name:");
        String newName = input.nextLine();
        return Map.entry("Name", newName);
    }

    /** Form to prompt the user for a new age to update in their profile.
     * @return map entry storing the new age number.
     */
    protected static Map.Entry<String, String> showAgeChangeForm() {
        System.out.println("Enter new Age:");
        int newAge = input.nextInt();
        input.next();
        return Map.entry("Age", String.valueOf(newAge));
    }

    /** Form to prompt the user for a new marital status to update in their profile
     * @return map entry storing the new marital status.
     */
    protected static Map.Entry<String, String> showMaritalStatusChangeForm() {
        String status = "";
        do {
            System.out.println("Change marital status:");
            System.out.println("Type 'Married' or 'Single' to change");
            status = input.nextLine();
        } while (!status.equalsIgnoreCase("married") && !status.equalsIgnoreCase("single"));

        return Map.entry("maritalStatus", status);
    }

    /** Form to prompt the user for the particular in their profile to update.
     * @return The choice field to update.
     */
    public static int toUpdateInfoForm() {
        int choice = 0;
        while (choice<1 || choice>4) {
            System.out.println("Select option to update:");
            System.out.println("1. Change Name");
            System.out.println("2. Change Age");
            System.out.println("3. Change Marital Status");
            System.out.println("4. Quit");
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
            }
            if (choice<1 || choice>4) {System.out.println("Invalid!");}
        }
        if (choice==4) {return 0;} else {return choice;}
    }

    /** Method to show the current booking of an applicant.
     * @param applicant The applicant profile to be displayed
     */
    public static void showApplicantBooking(Applicant applicant) {
        if (applicant.getFlatTypeBooked()!=null) {
            System.out.println("Currently Booked Flat: " + applicant.getProjectBooked().getName() + ", " + applicant.getFlatTypeBooked().getType());
        } else {System.out.println("Currently No Flat Booked");}
    }

    /** Method to show the currently assigned project of an officer
     * @param officer The officer profile to be displayed.
     */
    public static void showOfficerProj(HDBOfficer officer) {
        if (officer.getAssignedProject()!=null) {
            System.out.println("Currently Assigned Project: " + officer.getAssignedProject().getName());
        } else {System.out.println("Currently Assigned Project: None");}

    }

    /** Method to show the current project being handled by a manager
     * @param manager The manager profile to be displayed.
     */
    public static void showManagerInCharge(HDBManager manager) {
        if (manager.hasActiveProject()) {
            System.out.println("Currently In Charge Of: " + manager.getCurrentProject().getName());
        } else {System.out.println("Currently Not In Charge Of Any Project");}
    }
}
