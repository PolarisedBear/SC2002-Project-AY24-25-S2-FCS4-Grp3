package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.util.Map;
import java.util.Scanner;

public class UserView {
    private static Scanner input = new Scanner(System.in);
    public static void displayUserInfo(User user) {
        System.out.println(user.getInfo());
    };

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
    public static Map.Entry<String, String> showPasswordChangeForm() {
        System.out.println("Changing password:");
        System.out.println("Enter old password:");
        String oldpw = input.nextLine();
        System.out.println("Enter new password:");
        String newpw = input.nextLine();
        return Map.entry(oldpw, newpw);
    };

    public static void passwordChangeResult(boolean successful) {
        if (successful) {System.out.println("Password successfully changed");}
        else {System.out.println("Password not changed");}
    }

    protected static Map.Entry<String, String> showNameChangeForm() {
        System.out.println("Enter new Name:");
        String newName = input.nextLine();
        return Map.entry("Name", newName);
    }

    protected static Map.Entry<String, String> showAgeChangeForm() {
        System.out.println("Enter new Age:");
        int newAge = input.nextInt();
        input.next();
        return Map.entry("Age", String.valueOf(newAge));
    }

    protected static Map.Entry<String, String> showMaritalStatusChangeForm() {
        String status = "";
        do {
            System.out.println("Change marital status:");
            System.out.println("Type 'Married' or 'Single' to change");
            status = input.nextLine();
        } while (!status.equalsIgnoreCase("married") && !status.equalsIgnoreCase("single"));

        return Map.entry("maritalStatus", status);
    }

    public static int toUpdateInfoForm() {
        int choice = 0;
        while (choice<1 || choice>4) {
            System.out.println("Select option to update:");
            System.out.println("1. Change Name");
            System.out.println("2. Change Age");
            System.out.println("3. Change Marital Status");
            System.out.println("4. Quit");
            choice = input.nextInt();
            if (choice<1 || choice>4) {System.out.println("Invalid!");}
        }
        if (choice==4) {return 0;} else {return choice;}
    }

    public static void showApplicantBooking(Applicant applicant) {
        if (applicant.getFlatTypeBooked()!=null) {
            System.out.println("Currently Booked Flat: " + applicant.getProjectBooked().getName() + ", " + applicant.getFlatTypeBooked().getType());
        } else {System.out.println("Currently No Flat Booked");}
    }

    public static void showOfficerProj(HDBOfficer officer) {
        if (officer.getAssignedProject()!=null) {
            System.out.println("Currently Assigned Project: " + officer.getAssignedProject().getName());
        } else {System.out.println("Currently Assigned Project: None");}

    }

    public static void showManagerInCharge(HDBManager manager) {
        if (manager.hasActiveProject()) {
            System.out.println("Currently In Charge Of: " + manager.getCurrentProject().getName());
        } else {System.out.println("Currently Not In Charge Of Any Project");}
    }
}
