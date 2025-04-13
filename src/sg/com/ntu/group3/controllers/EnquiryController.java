package sg.com.ntu.group3.controllers;
import java.util.Map;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.EnquiryView;

public class EnquiryController implements EnquiryView, IEnquiryService{
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

    public void replyToEnquiry() {
    }

    @Override
    public void displayEnquiryForm() {

    }

    @Override
    public void displayEnquiryList() {

    }

    @Override
    public void showEditEnquiry() {

    }

    @Override
    public void showResponseForm() {

    }

    @Override
    public boolean submitEnquiry(Enquiry enquiry) {

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

    @Override
    public void replyToEnquiry(Enquiry enquiry, String response) {

    }
    public static void submitEnquiry(Applicant applicant, String content, Project project) {
        Enquiry enquiry = new Enquiry(project, content, applicant);
        enquiryMap.put(project, enquiry);
        System.out.println("submitted enquiry.");
    }
}
