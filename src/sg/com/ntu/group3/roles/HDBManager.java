package sg.com.ntu.group3.roles;

import enums.ApplicationStatus;
import sg.com.ntu.group3.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private List<HDBOfficer> Officers = new ArrayList<>();
    private Project currentProject;

    public HDBManager() {
        super();
    }

    public HDBManager(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    public void createProject(Project project) {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    public void editProject(Project project) {
        if (project == null){
            System.out.println("Proj not found");
            return;
        }
        project.setName(getName());

    }

    public boolean deleteProject(Project project) {
        return this.createdProjects.remove(project);
    }

    public void toggleProjectVisibility(Project project, boolean isVisible) {
        project.setVisible(isVisible);
    }

    public void viewAllProjects() {
        for (Project project : createdProjects) {
            System.out.println(project.toString());
        }
    }

    public void viewAllSelfProjects() {
        for (Project project : this.createdProjects) {
            System.out.println(project.toString());
        }
    }

    public void viewAllOfficerRegistration() throws IOException {
        Officers = UserRepository.getAllOfficers();
        for (HDBOfficer officer : Officers) {
            System.out.println(officer.getRegistrations());
        }
    }

    /*public void approveOfficerRegistration(Registration registration) {
        registration.approve();
    }*/

    public void rejectOfficerRegistration(Registration registration) {
        registration.reject();
    }

    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.Successful);
    }

    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.Unsuccessful);
    }

    public void approveWithdrawal(Application application) {
        application.setStatus(ApplicationStatus.Withdrawn);
        application.getApplicant().setApplication(null);
        application.getProject().getApplicants().remove(application.getApplicant());
        System.out.println("Withdrawal of application success");
    }

    public void rejectWithdrawal(Application application) {
        application.setStatus(ApplicationStatus.WithdrawnUnsuccessful);
        System.out.println("Withdrawal of application rejected");
    }

    public void generateReport(Project project, int number) {
        List <Applicant> applicants = project.getApplicants();
        Report report = new Report();
        report.generateApplicantBookingReport(number, applicants);
        
    }

    public void viewEnquiries() {
        for (Project project : createdProjects) {
            for (Enquiry enquiry : project.getEnquiries()) {
                System.out.println(enquiry.toString());
            }
        }

    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiry.reply(reply);
    }


}
