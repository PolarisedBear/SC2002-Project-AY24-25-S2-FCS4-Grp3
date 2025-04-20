package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EnquiryView implements View {
    private static Scanner input = new Scanner(System.in);

    public static Map.Entry<String, String> showCreateEnquiryForm() {
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
        View.lineSeparator();
    };
    public static void displayEnquiryList(Project project) {
        List<Enquiry> enquiryList = project.getEnquiries();
        for (Enquiry enquiry : enquiryList) {
            System.out.println(enquiry);
            System.out.println("\n");
        }
        View.lineSeparator();
    }
    public static String showResponseForm() {
        System.out.println("Enter your response to this enquiry:");
        return input.nextLine();
    };

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

    public static void showOperationOutcome(String action, boolean success) {
        if (success) System.out.println("Enquiry " +action+ " successful");
        else System.out.println("Enquiry " +action+ " unsuccessful");
    }

    public static int viewAndReplyForm(HDBOfficer officer) throws InputMismatchException {
        Project assignedProject = officer.getAssignedProject();
        System.out.println("Viewing and Replying to relevant enquiries");
        System.out.println("Currently assigned project:");
        ProjectView.displayProjectInfo(assignedProject);
        View.lineSeparator();
        System.out.println("Relevant Enquiries:");
        displayEnquiryList(assignedProject);
        System.out.println("Enter the ID of the enquiry you'd like to reply to:");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }

    public static int displayManagerEnquiryList(List<Enquiry> enquiries) {
        System.out.println("\n--- Enquiries for Your Projects ---");
        for (int i = 0; i < enquiries.size(); i++) {
            Enquiry e = enquiries.get(i);
            System.out.println("[" + i + "] " + e.getContent() +
                    " (from: " + e.getUser().getName() +
                    ", Project: " + e.getProj().getName() + ")");
        }
        System.out.print("Enter the index of the enquiry to reply (or -1 to cancel): ");
        int choice = input.nextInt();
        input.nextLine();
        return choice;
    }
}
