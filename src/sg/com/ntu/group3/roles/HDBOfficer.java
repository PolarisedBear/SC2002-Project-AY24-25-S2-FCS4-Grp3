package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.controllers.ApplicationController;
import sg.com.ntu.group3.controllers.EnquiryController;
import sg.com.ntu.group3.controllers.HDBOfficerController;
import sg.com.ntu.group3.controllers.ProjectController;
import sg.com.ntu.group3.controllers.ReportController;
import sg.com.ntu.group3.controllers.WithdrawalController;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;

import java.util.List;
import java.util.Map;

import enums.ApplicationStatus;

public class HDBOfficer extends Applicant {
    private Applicant applicantProfile = null;
    private Project assignedProject;
    private List<Registration> registrations;
    private List<Application> applications;
    private ProjectController projectController;
    private HDBOfficerController officerController;
    private ApplicationController applicationController;
    private ReportController reportController;
    private EnquiryController enquiryController;
    private WithdrawalController withdrawalController;


    public HDBOfficer() {
        super();
    }
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

    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    public void viewRegistrationStatus() {
        officerController.viewRegistrationStatus(this);
    }

    public void viewProjectDetails() {
        officerController.viewProjectDetails(this);
    }

    /*public void registerForProject(HDBOfficer officer, Project project) {
        Registration newRegistration = new Registration(project);
        registrations.add(newRegistration);
    }*/

    public Application findApplicationByNRIC(String nric) {
        for (Application application : applications) {
            if (application.getApplicant().getNric().equals(nric)) {
                return application.getApplication();
            }
        }
        System.out.println("No application found");
        return null;
    }

    public void updateApplication(Application application) {
        /*if (application == null) {
            System.out.println("Application is null.");
            return;
        } */
        if (assignedProject == null) {
            System.out.println("No project assigned to this officer.");
            return;
        }
        if(!application.getProject().equals(assignedProject)){
            System.out.println("the officer is not assigned to this project");
            return;
        }
        
        if (application.getStatus() == ApplicationStatus.Booking
        && application.getApplicant().getFlatTypeBooked() == null 
        && application.getApplicant().getProjectBooked() == null) {
            applicantProfile = application.getApplicant();
            FlatType flatType = application.getBookedFlat();
            //Map<FlatType, Integer> availUnits = assignedProject.getUnitsAvailable();

            //if(availUnits.containsKey(flatType) && availUnits.get(flatType)> 0){
                application.setStatus(ApplicationStatus.Booked);
                assignedProject.getUnitsAvailable().put(flatType, availUnits.get(flatType) - 1);

                applicantProfile.setFlatTypeBooked(flatType);
                applicantProfile.setProjectBooked(assignedProject);
                System.out.println("Flat booked for" + application.getApplicant().getName());
            }
        } else {
            System.out.println("no application found");
        }

    }

    public void updateProject(Application application) {
        //not sure if this is needed

    }

    public void generateReceipt(Application application) {
       System.out.println("Name: " + application.getApplicant().getName() +"\nNRIC: " + application.getApplicant().getNric() +
                "\nProj name: " + application.getProject().getName() +"\nFlat Type: " + application.getFlatType() +
                "\nStatus: " + application.getStatus() +"\nUnits Avail: " + application.getProject().getUnitsAvailable());


    }

    public void viewEnquiries() {
        for (Enquiry enquiry : applicantProfile.getEnquiries()) {
            System.out.println("Enquiry: " + enquiry.getContent() 
            + "\nStatus: " + enquiry.getStatus());
        }

    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiry.reply(reply);
    }
    
    public void assignProject(Project project){
        this.assignedProject = project;
    }
    public List<Registration> getRegistrations() {
        return registrations;
    }
    public Project getAssignedProject() {
        return assignedProject;
    }

    

}
