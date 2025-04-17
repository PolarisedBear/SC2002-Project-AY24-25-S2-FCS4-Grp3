package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

public interface IOfficerService {
    boolean registerForProject(HDBOfficer officer);
    String getRegistrationStatus(HDBOfficer officer, Project project);
}
