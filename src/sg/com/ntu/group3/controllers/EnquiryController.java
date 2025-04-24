package sg.com.ntu.group3.controllers;
import java.util.*;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.EnquiryView;
import sg.com.ntu.group3.views.View;


/** Enquiry Controller class for handling operations involving enquiries, such as creating, editing, replying and deleting
 *
 */
public class EnquiryController implements IEnquiryService{

    private final Scanner input = new Scanner(System.in);


    public EnquiryController() {
    }


    /** Method for submitting new enquiries as an applicant. Calls methods from the EnquiryView class to display forms to prompt user input.
     * Used whenever the current applicant user wishes to submit a new enquiry
     * @param applicant Applicant object that the new enquiry is submitted from
     */
    public void newEnquirySubmission(Applicant applicant) {
        Map.Entry<String, String> enquiryContents;
        do {
            enquiryContents = EnquiryView.showCreateEnquiryForm();
            EnquiryView.showOperationOutcome("creation", Project.projectExists(enquiryContents.getKey()));
        } while (!Project.projectExists(enquiryContents.getKey()));
        Project proj = Project.findProject(enquiryContents.getKey());
        createNewEnquiry(applicant, enquiryContents.getValue(), proj);

    }


    /** Main method to control the editing, viewing and deletion of applicant-submitted enquiries
     * Used whenever the applicant user wishes to modify existing enquiries they have made.
     * @param applicant The applicant whose enquiries are retrieved from
     */
    public void editViewAndDelete(Applicant applicant) {
        if (!applicant.hasEnquiries()) {
            EnquiryView.showOperationOutcome("Search", false);
            System.out.println("No enquiries found!");
            return;
        }
        String choice = EnquiryView.showEditViewAndDeleteMainApplicant();
        switch (choice) {
            case "1" -> EnquiryView.displayEnquiryList(applicant);
            case "2" -> {
                EnquiryView.displayEnquiryList(applicant);
                int id = EnquiryView.requestEnquiryId("edit");
                Enquiry enquiry = applicant.findEnquiry(id);
                if (enquiry!=null) {
                    editEnquiry(enquiry);
                } else {EnquiryView.showOperationOutcome("Retrieval", false);}
            }
            case "3" -> {
                EnquiryView.displayEnquiryList(applicant);
                int id = EnquiryView.requestEnquiryId("delete");
                Enquiry enquiry = applicant.findEnquiry(id);
                deleteEnquiry(enquiry);
            }
            default -> System.out.println("Cancelled");
        }
    }


    /** Method implemented from IEnquiryService. This implementation handles the editing of enquiry, modifying its content String.
     * Taking the specific enquiry to be edited as a parameter, then calling the enquiry's internal edit method.
     * @param enquiry The enquiry object to be edited.
     */
    @Override
    public void editEnquiry(Enquiry enquiry) {
        EnquiryView.showEditEnquiryForm();
        String editedResponse = input.nextLine();
        enquiry.editEnquiry(editedResponse);
    }

    /** Method implemented from IEnquiryService. This implementation handles enquiry deletion.
     * Taking the specific enquiry to be deleted as a parameter, then calling its internal delete method.
     * @param enquiry The enquiry object to be deleted
     */
    @Override
    public void deleteEnquiry(Enquiry enquiry) {
        enquiry.deleteEnquiry();
        System.out.println("deleted enquiry.");
    }


    /** Method implemented from IEnquiryService. This implementation handles creating new replies to existing enquiry.
     * This is done by entering the enquiry and the reply to it as parameters, then calling the enquiry's internal reply method.
     * @param enquiry The enquiry object to be modified
     * @param reply The String reply
     */
    @Override
    public void replyToEnquiry(Enquiry enquiry,String reply) {
        enquiry.reply(reply);
    }

    /** Method for creating a new enquiry. This includes adding the newly created enquiry to related projects for filtering
     * @param applicant The applicant who creates this enquiry
     * @param content The String content of the new enquiry
     * @param project The project for which this enquiry is about
     */
    public void createNewEnquiry(Applicant applicant, String content, Project project) {
        Enquiry enquiry = new Enquiry(project, content, applicant);
        EnquiryView.displayEnquirySubmit();
        if (Enquiry.getEnquiryMap().containsKey(project)) {
            Enquiry.getEnquiryMap().get(project).add(enquiry);
        } else {
            Enquiry.getEnquiryMap().put(project, new ArrayList<>());
            Enquiry.getEnquiryMap().get(project).add(enquiry);
        }
    }


    /** Overloaded method for viewing and replying to enquiries for HDB Officers. Calls EnquiryView to gather user inputs,
     * and display enquiries for the officer's assigned project, before calling replyToEnquiry to modify the enquiry.
     * @param officer The HDBOfficer who makes the reply
     */
    public void viewAndReplyToEnquiries(HDBOfficer officer) {
        Project assignedProject = officer.getAssignedProject();
        try {
            if (assignedProject != null) {
                int inputID = EnquiryView.viewAndReplyForm(officer);
                if (assignedProject.findEnquiry(inputID) != null) {
                    Enquiry enquiry = assignedProject.findEnquiry(inputID);
                    System.out.println(enquiry);
                    String response = EnquiryView.showResponseForm();
                    replyToEnquiry(enquiry, response);
                    EnquiryView.showOperationOutcome("Response", true);
                } else {
                    EnquiryView.showOperationOutcome("Reponse", false);
                    System.out.println("Invalid input!");
                }
            } else {
                EnquiryView.showOperationOutcome("Retrieval", false);
                System.out.println("No project assigned!");
            }
        } catch (InputMismatchException e) {
            View.showOperationOutcome("Enquiry Retrieval", false);
            System.out.println("Invalid Input!");
            input.nextLine();
        }

    }


    /** Overloaded method for viewing and replying to enquiries for HDB Managers. Calls EnquiryView to display enquiries from all project,
     * gather user input and calls replyToEnquiry to modify the enquiry
     * @param manager The HDB Manager who makes the reply.
     */
    public void viewAndReplyToEnquiries(HDBManager manager) {
        List<Project> allProjects = Project.getProjectList();

        List<Enquiry> relevantEnquiries = new ArrayList<>();
        for (Project project : allProjects) {
            List<Enquiry> projectEnquiries = Enquiry.getEnquiryMap().getOrDefault(project, List.of());
            relevantEnquiries.addAll(projectEnquiries);
        }

        if (relevantEnquiries.isEmpty()) {
            System.out.println("No enquiries.");
            return;
        }

        int selectedIndex = EnquiryView.displayManagerEnquiryList(relevantEnquiries);
        if (selectedIndex < 0 || selectedIndex >= relevantEnquiries.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Enquiry selectedEnquiry = relevantEnquiries.get(selectedIndex);
        System.out.println(selectedEnquiry);
        String response = EnquiryView.showResponseForm();
        replyToEnquiry(selectedEnquiry, response);
        View.showOperationOutcome("Response", true);
    }
}
