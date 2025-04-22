package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.HDBOfficer;

/** Manager Interface for Officer Approval
 *  Implemented by HDBManagers, can be extended for other high level roles.
 *
 */
public interface IManagerService {
    void approveOfficer(HDBOfficer officer, Registration registration);
}
