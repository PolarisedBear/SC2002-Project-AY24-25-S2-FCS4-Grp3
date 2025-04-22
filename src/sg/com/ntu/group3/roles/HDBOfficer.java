package sg.com.ntu.group3.roles;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Enquiry;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.ApplicationStatus;

public class HDBOfficer extends Applicant{
    private Applicant applicantProfile = null;
    private Project assignedProject;
    private List<Registration> registrations = new ArrayList<>();
    private List<Application> applications = new ArrayList<>();


    public HDBOfficer() {
        super();
    }

    public HDBOfficer(String name, String nric, int age, String maritalStatus, String password) {
        super(name, nric, age, maritalStatus, password);
    }

    /*public void viewRegistrationStatus() {
        officerController.viewRegistrationStatus(this);
    }

    public void viewProjectDetails() {
        officerController.viewProjectDetails(this);
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


    public void generateReceipt(Application application) {
        System.out.println("Name: " + application.getApplicant().getName() +"\nNRIC: " + application.getApplicant().getNric() +
                "\nProj name: " + application.getProject().getName() +"\nFlat Type: " + application.getProject().getFlatTypes() +
                "\nStatus: " + application.getStatus() +"\nUnits Avail: " + application.getProject().getUnitsAvailable());


    }

    public void viewEnquiries() {
        for (Enquiry enquiry : applicantProfile.getEnquiries()) {
            System.out.println("Enquiry: " + enquiry.getContent());
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
    public void register(Registration registration) {registrations.add(registration);}

    public boolean canRegisterForProject(Project project) {
        boolean hasRegisteredForProject = registrations.stream()
        .anyMatch(reg -> reg.getProject().equals(project));
        if (super.getApplication()==null && !hasRegisteredForProject) {return true;} else {
            return !project.toString().equalsIgnoreCase(super.getApplication().getProject().toString());
        }
    }

    public boolean canApplyForProject(Project project) {
        if(!super.canApplyForProject() || applications.isEmpty()) {return false;}
        for (Application application : applications) {
            if (application.getApplicant().getNric().equals(this.getNric())) {
                System.out.println("Officer has an application, unable to apply for project.");
                return false;
            }
        }
        boolean hasRegisteredForProject = registrations.stream()
        .anyMatch(reg -> reg.getProject().equals(project));

        /*check if officer has a registration for the project */
        if (assignedProject == project && !hasRegisteredForProject) {
            System.out.println("officer cannot apply to his assigned project.");
            return false;
        }

        return true;
    }


}
