package sg.com.ntu.group3.controllers.services;

import enums.ApplicationStatus;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;

public interface IApplicationService {
    public void withdrawApplication(Application application);
    public ApplicationStatus getApplicationStatus(Application application);
    public boolean bookFlat(Applicant applicant);
}
