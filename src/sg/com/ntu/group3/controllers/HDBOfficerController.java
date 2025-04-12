package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.HDBOfficer;

public class HDBOfficerController implements IManagerService {
    private IOfficerService officerService;

    public HDBOfficerController(IOfficerService officerService) {
        this.officerService = officerService;
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

    public void approveOfficer(Registration registration) {
        registration.approve();
    }


}
