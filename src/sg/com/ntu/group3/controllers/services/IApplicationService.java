package sg.com.ntu.group3.controllers.services;

import enums.ApplicationStatus;

public interface IApplicationService {
    public void updateProfile(User user);
    public void withdrawApplication(Application application);
    public ApplicationStatus getApplicationStatus(Application application);
    public boolean bookFlat(Application application, FlatType flatType);
}
