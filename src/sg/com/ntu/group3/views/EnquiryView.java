package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;

import java.util.Map;
import java.util.Scanner;

public class EnquiryView implements View {
    private static Scanner input = new Scanner(System.in);

    public static Map.Entry<String, String> showEnquiryForm() {
        System.out.println("Create a new Enquiry: Find a project to enquire about\n");
        System.out.println(Project.getProjectList());
        System.out.println("\nEnter the name of the project you wish to enquire about");
        String proj = input.nextLine();
        System.out.println("Enter the content of your query");
        String content = input.nextLine();
        return Map.entry(proj, content);
    };
    public static void displayEnquiryList(Applicant applicant) {
        for (Enquiry enquiry : applicant.getEnquiries()) {
            System.out.println(enquiry);
            System.out.println("\n");
        }
    };
    public void showResponseForm() {};

    public static String showEditReplyAndDeleteMainApplicant() {
        System.out.println("Edit, Reply to and Delete your own enquiries");
        System.out.println("Select your action:");
        System.out.println("1. View Enquiries");
        System.out.println("2. Edit enquiry");
        System.out.println("3. Delete enquiry");
        System.out.println("Enter any other key to cancel");
        return input.nextLine();

    }

    public static int requestEnquiryId(String action) {
        System.out.println("Enter the id of the enquiry to " + action);
        int id = input.nextInt();
        input.nextLine();
        return id;
    }
    
    public static void displayEnquirySubmit(){
        System.out.println("Enquiry submitted");
    }
    public static void showEditEnquiryForm(){
        System.out.println("Enter the Edit Enquiry Form");
    };

    public static void showOperationOutcomes(String action, boolean success) {
        if (success) System.out.println("Enquiry " +action+ " successful");
        else System.out.println("Enquiry " +action+ " unsuccessful");
    }
}
