package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Enquiry View class is responsible for displaying information and gathering user input for operations related to enquiries.
 * <p>Includes methods used by all user types</p>
 *
 */
public class EnquiryView implements View {
    private static Scanner input = new Scanner(System.in);

    /** Method to gather input from the user to create a new enquiry.
     * @return A map entry storing the name of the project to be enquired and the content of the new enquiry
     */
    public static Map.Entry<String, String> showCreateEnquiryForm() {
        System.out.println("Create a new Enquiry: Find a project to enquire about\n");
        System.out.println(Project.getProjectList());
        System.out.println("\nEnter the name of the project you wish to enquire about");
        String proj = input.nextLine();
        System.out.println("Enter the content of your query");
        String content = input.nextLine();
        return Map.entry(proj, content);
    };

    /** Overloaded method to display a list of enquiries made by an applicant. Used by applicants for reviewing their own enquiries
     * @param applicant The applicant to retrieve the enquiries from.
     */
    public static void displayEnquiryList(Applicant applicant) {
        for (Enquiry enquiry : applicant.getEnquiries()) {
            System.out.println(enquiry);
            System.out.println("\n");
        }
        View.lineSeparator();
    };

    /** Overloaded method for displaying a list of enquiries made to a project. Used by officers for enquiry response.
     * @param project The project to retrieve enquiries from.
     */
    public static void displayEnquiryList(Project project) {
        List<Enquiry> enquiryList = project.getEnquiries();
        for (Enquiry enquiry : enquiryList) {
            System.out.println(enquiry);
            System.out.println("\n");
        }
        View.lineSeparator();
    }

    /** Method to gather user input for replying to an enquiry. Used by officers and managers.
     * @return The reply string
     */
    public static String showResponseForm() {
        System.out.println("Enter your response to this enquiry:");
        return input.nextLine();
    };

    /** Method to show the main user interface for editing, viewing and deletion of enquiries for an applicant user.
     * @return The int choice indicating which action to take next.
     */
    public static String showEditViewAndDeleteMainApplicant() {
        System.out.println("Edit, Reply to and Delete your own enquiries");
        System.out.println("Select your action:");
        System.out.println("1. View Enquiries");
        System.out.println("2. Edit enquiry");
        System.out.println("3. Delete enquiry");
        System.out.println("Enter any other key to cancel");
        return input.nextLine();

    }

    /** Method to prompt the user for the id of an enquiry. Used by officers to select enquiries for review.
     * @param action The type of action to be taken in regard to the chosen enquiry
     * @return The int enquiryId of the chosen enquiry
     */
    public static int requestEnquiryId(String action) {
        int id = 0;
        System.out.println("Enter the id of the enquiry to " + action);
        try {
            id = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            showOperationOutcome("Retrieval", false);
            input.nextLine();
        }

        return id;
    }

    /** Displays the successful enquiry submission
     */
    public static void displayEnquirySubmit(){
        System.out.println("Enquiry submitted");
    }

    /** Displays acknowledgement that the user is currently viewing the edit enquiry form
     */
    public static void showEditEnquiryForm(){
        System.out.println("Enter the Edit Enquiry Form");
    };

    /** Overloaded method from the View interface for showing the outcome of an enquiry-related operation.
     * @param action The operation performed
     * @param success true if operations was successful, false if otherwise.
     */
    public static void showOperationOutcome(String action, boolean success) {
        if (success) System.out.println("Enquiry " +action+ " successful");
        else System.out.println("Enquiry " +action+ " unsuccessful");
    }

    /** Method to prompt user input to choose an enquiry to reply to
     * @param officer The officer making the reply
     * @return the integer choice of the enquiry ID to be processed
     * @throws InputMismatchException exception is thrown if the user input a non-integer.
     */
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

    /** Method to display the list of enquiries made to a manager's current project, and gather input for which enquiry to reply to.
     * @param enquiries The list of enquiries taken from the manager's current project
     * @return the index of their chosen enquiry from the list.
     */
    public static int displayManagerEnquiryList(List<Enquiry> enquiries) {
        System.out.println("\n--- Enquiries for Your Projects ---");
        for (int i = 0; i < enquiries.size(); i++) {
            Enquiry e = enquiries.get(i);
            System.out.println("[" + i + "] " + e.getContent() +
                    " (from: " + e.getUser().getName() +
                    ", Project: " + e.getProj().getName() + ")");
        }
        System.out.print("Enter the index of the enquiry to reply (or -1 to cancel): ");
        int choice = -1;
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Enquiry Display", false);
            input.nextLine();
        }

        return choice;
    }
}
