package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

public interface IOfficerService {
    public boolean registerForProject(HDBOfficer officer, Project project);
    public String getRegistrationStatus(HDBOfficer officer, Project project);
}
