package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

public interface IOfficerService {
    void registerForProject(HDBOfficer officer);
    void viewRegistrationStatus(HDBOfficer officer);
}
