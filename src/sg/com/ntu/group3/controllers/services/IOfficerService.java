package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.HDBOfficer;

/** Officer Service Interface including methods for officer registration
 * Implemented by HDBOfficer Controller
 *
 */
public interface IOfficerService {
    void registerForProject(HDBOfficer officer);
    void viewRegistrationStatus(HDBOfficer officer);
}
