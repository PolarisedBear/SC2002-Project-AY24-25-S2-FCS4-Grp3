package sg.com.ntu.group3.controllers;
import java.util.Map;
import java.util.Scanner;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.EnquiryView;

public class EnquiryController implements EnquiryView, IEnquiryService{
    private IEnquiryService enquiryService;
    private static Map<Project, sg.com.ntu.group3.models.Enquiry> enquiryMap;
     private Scanner input = new Scanner(System.in);


    public EnquiryController() {}


    @Override
    public void displayEnquiryForm() {

    }

    @Override
    public void displayEnquiryList() {
        for (Map.Entry<Project, Enquiry> entry : enquiryMap.entrySet()) {
            Project project = entry.getKey();
            Enquiry enquiry = entry.getValue();
            System.out.println("Project: " + project.getName() + ", Enquiry: " + enquiry.getContent());
        }
    }

    @Override
    public void showEditEnquiry() {

    }

    @Override
    public void showResponseForm() {

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

    public void submitEnquiry(Applicant applicant, String content, Project project) {
        Enquiry enquiry = new Enquiry(project, content, applicant);
        EnquiryView.displayEnquirySubmit();
        applicant.addEnquiry(enquiry);
        enquiryMap.put(project, enquiry);
       
    }
}
