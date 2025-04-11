package sg.com.ntu.groupX.controllers.services;

public interface IOfficerService {
    public boolean registerForProject();
    public String getRegistrationStatus(HDBOfficer officer, Project project);
}
