package sg.com.ntu.group3.roles;

import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.ApplicationController;
import sg.com.ntu.group3.controllers.EnquiryController;
import sg.com.ntu.group3.controllers.HDBOfficerController;
import sg.com.ntu.group3.controllers.ProjectController;
import sg.com.ntu.group3.controllers.ReportController;
import sg.com.ntu.group3.controllers.WithdrawalController;
import sg.com.ntu.group3.models.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private List<HDBOfficer> Officers = new ArrayList<>();
    private Project currentProject;
    private ProjectController projectController;
    private HDBOfficerController officerController;
    private ApplicationController applicationController;
    private ReportController reportController;
    private EnquiryController enquiryController;
    private WithdrawalController withdrawalController;

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

    public void setCurrentProject(Project currentProject) {this.currentProject = currentProject;}

    public void setProjectController(ProjectController projectController) {
        this.projectController = projectController;
    }
    public void setOfficerController(HDBOfficerController officerController) {
        this.officerController = officerController;
    }
    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    public void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }
    public void setEnquiryController(EnquiryController enquiryController) {
        this.enquiryController = enquiryController;
    }
    public void setWithdrawalController(WithdrawalController withdrawalController) {
        this.withdrawalController = withdrawalController;
    }


    public void createProject(Project project) throws ParseException {
        project.setCreatedBy(this.getName());
        this.createdProjects.add(project);
    }

    public void editProject(Project project) throws ParseException {
        projectController.editProject(project);

    }

    public void deleteProject() {
        projectController.deleteProject();
    }

    public void toggleProjectVisibility(Project project, boolean isVisible) {
        projectController.toggleVisibility(project, isVisible);
    }

    public void viewAllProjects() {
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
        officerController.viewAllOfficerRegistration(Officers);
    }

    public void approveOfficerRegistration(HDBOfficer officer, Registration registration) {
        officerController.approveOfficer(officer,registration);
    }

    public void rejectOfficerRegistration(Registration registration) {
        officerController.rejectOfficer(registration);
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
        
    }

    public void viewEnquiries() {
        enquiryController.displayEnquiryList();
    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiryController.replyToEnquiry(enquiry, reply);
    }

}
