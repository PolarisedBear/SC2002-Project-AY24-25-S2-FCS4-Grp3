package sg.com.ntu.group3.controllers.services;

public interface IOfficerService {
    public boolean registerForProject();
    public String getRegistrationStatus(HDBOfficer officer, Project project);
}
