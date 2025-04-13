package sg.com.ntu.group3.controllers;

import java.util.List;

import enums.RegistrationStatus;
import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.models.UserRepository;
import sg.com.ntu.group3.roles.HDBOfficer;

public class HDBOfficerController implements IManagerService, IOfficerService {
    private Session session;

    public HDBOfficerController(Session session) {
        this.session = session;
    }

    public boolean registerForProject(HDBOfficer officer, Project project) {
        if(officer.canApplyforproject(project) && project.isWithinApplicationPeriod(project.getCloseDate())){
            Registration newRegistration = new Registration(project);
            officer.getRegistrations().add(newRegistration);
            return true;

        }
        return false;
    }

    public void approveOfficer(HDBOfficer officer, Registration registration) {
        if (!registration.getStatus().toString().equals("Pending")) {
            System.out.println("registration was rejected or not pending");
            return;
        }
        Project project = registration.getProject();
        if (project.assignOfficer(officer)){
            officer.assignProject(registration.getProject());
            project.assignOfficer(officer);
            registration.approve();
            System.out.println("officer approved for " + project.getName());
        }
        else{
            System.out.println("approval for officer failed");
        }
    }
    public void rejectOfficer(Registration registration) {
        registration.reject();
        System.out.println("officer rejected for " + registration.getProject().getName());
    }


    public void viewProjectDetails(HDBOfficer officer) {
        Project project = officer.getAssignedProject();
        if (project != null) {
            System.out.println("Project Name: " + project.getName() +
            "units avail: " + project.getUnitsAvailable() + 
            "Project Opening Date: " + project.getOpeningDate() +
            "Project Closing Date: " + project.getCloseDate() +
            "Flat Types: " + project.getFlatTypes());

        } else {
            System.out.println("No project assigned to the officer.");
        }
    }
    public void viewRegistrationStatus(HDBOfficer officer) {
        for (Registration registration : officer.getRegistrations()) {
            System.out.println("Project: " + registration.getProject().getName() 
            + ", Status: " + registration.getStatus());
        }
    }

    public void viewAllOfficerRegistration(List<HDBOfficer> officers) {
        for (HDBOfficer officer : officers) {
            for (Registration registration : officer.getRegistrations()) {
                String status= getRegistrationStatus(officer, registration.getProject());
                if (status != null) {
                    System.out.println("Officer: " + officer.getName() + ", Project: " + registration.getProject().getName() + ", Registration Status: " + status);
                }
            }
        }
        /*for (HDBOfficer officer : officers) {
            for (Registration registration : officer.getRegistrations()) {
                if (registration.getStatus().equals("Pending") 
                || registration.getStatus().equals("Approved")) {
                    System.out.println("Officer: " + officer.getName() + ", Registration Status: " + registration.getStatus());
                }
            }
        }*/
    }
        public String getRegistrationStatus(HDBOfficer officer, Project project) {
        for (Registration registration : officer.getRegistrations()) {
            if (project == null || registration.getProject().equals(project)) {
                if (registration.getStatus().toString().equals("Pending") 
                || registration.getStatus().toString().equals("Approved")) {
                    return registration.getStatus().toString();
                }
            }
        }
        return null;
    }
}
