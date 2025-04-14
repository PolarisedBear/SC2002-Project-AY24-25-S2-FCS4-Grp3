package sg.com.ntu.group3.controllers;
import java.util.Map;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.EnquiryView;

public class EnquiryController implements IEnquiryService{
    private IEnquiryService enquiryService;
    private static Map<Project, sg.com.ntu.group3.models.Enquiry> enquiryMap;

    public EnquiryController(IEnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }
    public EnquiryController() {}

    public void submitEnquiry() {
    }

    public void editEnquiry() {
    }

    public void deleteEnquiry() {
    }


    public void displayEnquiryList() {
        for (Map.Entry<Project, Enquiry> entry : enquiryMap.entrySet()) {
            Project project = entry.getKey();
            Enquiry enquiry = entry.getValue();
            System.out.println("Project: " + project.getName() + ", Enquiry: " + enquiry.getContent());
        }
    }

    @Override
    public boolean submitEnquiry(Enquiry enquiry) {
        return false;
    }

    @Override
    public void editEnquiry(Enquiry enquiry) {
        EnquiryView.showEditEnquiry();
        enquiry.editEnquiry(enquiry.getContent());
        System.out.println("edited enquiry.");

    }

    @Override
    public void deleteEnquiry(Enquiry enquiry) {
        enquiry.deleteEnquiry();
        System.out.println("deleted enquiry.");
    }

    public void replyToEnquiry(Enquiry enquiry,String reply) {
        enquiry.reply(reply);
    }

    public void submitEnquiry(Applicant applicant, String content, Project project) {
        Enquiry enquiry = new Enquiry(project, content, applicant);
        enquiryMap.put(project, enquiry);
        System.out.println("submitted enquiry.");
    }
}
