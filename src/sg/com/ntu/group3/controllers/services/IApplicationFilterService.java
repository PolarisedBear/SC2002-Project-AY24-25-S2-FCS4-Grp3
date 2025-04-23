package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;

import java.util.List;
import java.util.Map;

/** An interface containing methods to filter lists of applications.
 *  The methods filter a given list by different conditions, returning a filtered list
 */
public interface IApplicationFilterService {
    List<Application> filterByMaritalStatus(boolean married, List<Application> applicationList);
    List<Application> filterByAge(int minAge, int maxAge, List<Application> applicationList);
    List<Application> filterByFlatType(String flatType, List<Application> applicationList);
    Application filterByNRIC(String nric, List<Application> applications);
}
