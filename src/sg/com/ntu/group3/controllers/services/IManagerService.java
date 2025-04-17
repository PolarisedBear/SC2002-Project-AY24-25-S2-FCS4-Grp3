package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.HDBOfficer;

public interface IManagerService {
    void approveOfficer(HDBOfficer officer, Registration registration);
}
