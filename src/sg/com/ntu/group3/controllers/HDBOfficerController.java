package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.HDBOfficer;

public class HDBOfficerController implements IManagerService {
    private Session session;

    public HDBOfficerController(Session session) {
        this.session = session;
    }

    public void registerForProject(HDBOfficer officer, Project project) {
        
        for(Registration registration : officer.getRegistrations()){
            if(registration.getProject() == project){
                System.out.println("Already registered for the project");
                return;
            }
        }
        officer.getRegistrations().add(new Registration(project));
    }

    public void approveOfficer(HDBOfficer officer, Registration registration) {
        Project project = registration.getProject();
        if (project.assignOfficer(officer)){
            officer.assignProject(registration.getProject());
            project.assignOfficer(officer);
            registration.approve();
            System.out.println("Officer approved for " + project.getName());
        }
        else{
            System.out.println("approval for officer failed");
        }


    }


    @Override
    public void approveOfficer(Registration registration) {

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

    public void
}
