package sg.com.ntu.group3.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.controllers.services.IEnquiryService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.views.EnquiryView;

public class EnquiryController implements IEnquiryService{

    private Session session;
    private Scanner input = new Scanner(System.in);


    public EnquiryController(Session session) {this.session = session;}


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
        for (Map.Entry<Project, List<Enquiry>> entry : Enquiry.getEnquiryMap().entrySet()) {
            Project project = entry.getKey();
            List<Enquiry> enquiryList = entry.getValue();
            for (Enquiry enquiry : enquiryList) {
                System.out.println("Project: " + project.getName() + ", Enquiry: " + enquiry.getContent());
            }
        }
    }

    public void editReplyAndDelete(Applicant applicant) {
        if (!applicant.hasEnquiries()) {
            EnquiryView.showOperationOutcomes("Search", false);
            System.out.println("No enquiries found!");
            return;
        }
        String choice = EnquiryView.showEditReplyAndDeleteMainApplicant();
        switch (choice) {
            case "1" -> {EnquiryView.displayEnquiryList(applicant);}
            case "2" -> {
                EnquiryView.displayEnquiryList(applicant);
                int id = EnquiryView.requestEnquiryId("edit");
                Enquiry enquiry = applicant.findEnquiry(id);
                editEnquiry(enquiry);
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



    @Override
    public boolean submitEnquiry(Enquiry enquiry) {
        return false;
    }

    @Override
    public void editEnquiry(Enquiry enquiry) {
        EnquiryView.showEditEnquiryForm();
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
        if (Enquiry.getEnquiryMap().containsKey(project)) {
            Enquiry.getEnquiryMap().get(project).add(enquiry);
        } else {
            Enquiry.getEnquiryMap().put(project, new ArrayList<>());
            Enquiry.getEnquiryMap().get(project).add(enquiry);
        }
    }
}
