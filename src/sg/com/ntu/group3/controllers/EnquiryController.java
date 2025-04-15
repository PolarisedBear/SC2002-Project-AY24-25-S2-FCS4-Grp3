package sg.com.ntu.group3.controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.EnquiryView;

public class EnquiryController implements IEnquiryService{
    private static Map<Project, Enquiry> enquiryMap = new HashMap<>();
    private Scanner input = new Scanner(System.in);


    public EnquiryController() {}


    public void newEnquirySubmission(Applicant applicant) {
        Map.Entry<String, String> enquiryContents;
        do {
            enquiryContents = EnquiryView.showEnquiryForm();
            if (Project.projectExists(enquiryContents.getKey())) {
                EnquiryView.showOperationOutcomes("creation", true);
            } else {
                EnquiryView.showOperationOutcomes("creation", false);
            }
        } while (!Project.projectExists(enquiryContents.getKey()));
        Project proj = Project.findProject(enquiryContents.getKey());
        createNewEnquiry(applicant, enquiryContents.getValue(), proj);

    }

    public void displayEnquiryList() {
        for (Map.Entry<Project, Enquiry> entry : enquiryMap.entrySet()) {
            Project project = entry.getKey();
            Enquiry enquiry = entry.getValue();
            System.out.println("Project: " + project.getName() + ", Enquiry: " + enquiry.getContent());
        }
    }


    public void showEditEnquiry() {

    }


    public void showResponseForm() {

    }

    @Override
    public boolean submitEnquiry(Enquiry enquiry) {
        return false;
    }

    @Override
    public void editEnquiry(Enquiry enquiry) {
        EnquiryView.showEditEnquiry();
        String editedResponse = input.nextLine();
        enquiry.editEnquiry(editedResponse);
    }

    @Override
    public void deleteEnquiry(Enquiry enquiry) {
        enquiry.deleteEnquiry();
        System.out.println("deleted enquiry.");
    }
    //needs to be done
    public void replyToEnquiry(Enquiry enquiry,String reply) {
        enquiry.reply(reply);
    }

    public void createNewEnquiry(Applicant applicant, String content, Project project) {
        Enquiry enquiry = new Enquiry(project, content, applicant);
        EnquiryView.displayEnquirySubmit();
        applicant.addEnquiry(enquiry);
        enquiryMap.put(project, enquiry);
       
    }
}
