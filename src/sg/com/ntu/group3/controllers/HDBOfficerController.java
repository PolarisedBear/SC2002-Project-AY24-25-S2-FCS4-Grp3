package sg.com.ntu.group3.controllers;
import IManagerService;
import IOfficerService;
public class HDBOfficerController implements IManagerService{
    private IOfficerService officerService;

    public HDBOfficerController(IOfficerService officerService) {
        this.officerService = officerService;
    }

    public void registerForProject() {

    }

    public void approveOfficer() {

    }
}
