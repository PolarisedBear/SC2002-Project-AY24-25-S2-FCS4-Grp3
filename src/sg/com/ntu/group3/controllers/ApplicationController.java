package sg.com.ntu.group3.controllers;
import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.services.IApplicationService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.User;

public class ApplicationController implements IApplicationService {

    public ApplicationController() {

    }

    private boolean validateOfficerEligibility(HDBOfficer officer, Project project) {
        return false;
    }

    public void applyForProject() {

    }

    public void withdrawApplication() {

    }

    public void approveApplication() {

    }

    public void bookFlat() {

    }

    public void getApplicationStatus() {

    }

    @Override
    public void updateProfile(User user) {

    }

    @Override
    public void withdrawApplication(Application application) {

    }

    @Override
    public ApplicationStatus getApplicationStatus(Application application) {
        return null;
    }

    @Override
    public boolean bookFlat(Application application, FlatType flatType) {
        return false;
    }
}
