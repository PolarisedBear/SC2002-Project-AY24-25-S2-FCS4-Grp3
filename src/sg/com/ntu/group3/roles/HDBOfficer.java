package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;

import java.util.List;

public class HDBOfficer extends User {
    private Applicant applicantProfile = null;
    private Project assignedProject;
    private List<Registration> registrations;

    public HDBOfficer() {
        super();
    }

    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    public void viewRegistrationStatus() {

    }

    public void viewProjectDetails() {
        System.out.println(assignedProject.toString());
    }

    public void registerForProject(Project project) {
        Registration newRegistration = new Registration(project);
        registrations.add(newRegistration);
    }

    public Application findApplicationByNRIC(String nric) {

    }

    public void updateApplication(Application application) {

    }

    public void updateProject(Application application) {

    }

    public void generateReceipt(Application application) {

    }

    public void viewEnquiries() {

    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiry.reply(reply);
    }

}
