package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;

import java.util.List;
import java.util.Map;

public interface IApplicationFilterService {
    List<Application> filterByMaritalStatus(boolean married);
    List<Application> filterByAge(int minAge, int maxAge);
    List<Application> filterByFlatType(String flatType);
    List<Application> filterByCompositeCriteria(Map criteria);
}
