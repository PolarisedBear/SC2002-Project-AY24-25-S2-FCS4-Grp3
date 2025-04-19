package sg.com.ntu.group3.roles;


import sg.com.ntu.group3.models.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private List<HDBOfficer> Officers = new ArrayList<>();
    private Project currentProject;

    //constructors
    public HDBManager() {
        super();
    }
    public HDBManager(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    //getters and setters

    public List<Project> getCreatedProjects() {return createdProjects;}

    public void setCreatedProjects(List<Project> createdProjects) {this.createdProjects = createdProjects;}

    public List<HDBOfficer> getOfficers() {return Officers;}

    public void setOfficers(List<HDBOfficer> officers) {Officers = officers;}

    public Project getCurrentProject() {return currentProject;}
    public void setCurrentProject(Project project) {this.currentProject = project;}
    public boolean hasActiveProject() {return currentProject!=null;}


    public void createProject(Project project) throws ParseException {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /* public void editProject(Project project) throws ParseException {
        projectController.findProjectsByManager(this);
        projectController.editProject(project);

    }

    public void deleteProject() {
        projectController.deleteProject();
    }*/

    /*public void toggleProjectVisibility(Project project, boolean isVisible) {
        project.setVisible(!isVisible);

    }*/

    /*public void viewAllProjects() {
        List<Project> AllProjects = projectController.getAllProjects();
        for (Project project : AllProjects) {
            System.out.println(project.toString());
        }
    }

    public void viewAllSelfProjects() {
        List<Project> createdProjects = projectController.findProjectsByManager(this);
        for (Project project : createdProjects) {
            System.out.println(project.toString());
        }
    }

    public void viewAllOfficerRegistration() {
       // officerController.viewAllOfficerRegistration(Officers);
    }

    public void approveOfficerRegistration(HDBOfficer officer, Registration registration) {
        officerController.approveOfficer(officer,registration);
    }

    public void rejectOfficerRegistration(Registration registration) {
       // officerController.rejectOfficer(registration);
    }

    public void approveApplication(Application application) {
        applicationController.approveApplication(application);
    }

    public void rejectApplication(Application application) {
        applicationController.rejectApplication(application);
    }

    public void approveWithdrawal(Application application) {
        withdrawalController.approveWithdrawal(application);
    }

    public void rejectWithdrawal(Application application) {
        withdrawalController.rejectWithdrawal(application);
    }

    public void generateReport(Project project, int number) {
       // reportController.generateReport(project, number);
        /*List <Applicant> applicants = project.getApplicants();
        Report report = new Report();
        report.generateApplicantBookingReport(number, applicants);*/
        /* 
    }

    public void viewEnquiries() {
        enquiryController.displayEnquiryList();
    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiryController.replyToEnquiry(enquiry, reply);
    }*/

}
