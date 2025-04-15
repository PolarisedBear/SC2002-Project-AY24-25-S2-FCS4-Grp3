package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Project;

import java.util.Map;
import java.util.Scanner;

public class EnquiryView implements View {
    private static Scanner input = new Scanner(System.in);

    public static Map.Entry<String, String> showEnquiryForm() {
        System.out.println("Create a new Enquiry: Find a project to enquire about\n");
        System.out.println(Project.getProjectList());
        System.out.println("\n Enter the name of the project you wish to enquire about");
        String proj = input.nextLine();
        System.out.println("Enter the content of your query");
        String content = input.nextLine();
        return Map.entry(proj, content);
    };
    public void displayEnquiryList() {};
    public void showResponseForm() {};

    
    public static void displayEnquirySubmit(){
        System.out.println("Enquiry submitted");
    }
    public static void showEditEnquiry(){
        System.out.println("Edit Enquiry Form");
    };

    public static void showOperationOutcomes(String action, boolean success) {
        if (success) System.out.println("Enquiry " +action+ " successful");
        else System.out.println("Enquiry " +action+ " unsuccessful");
    }
}
