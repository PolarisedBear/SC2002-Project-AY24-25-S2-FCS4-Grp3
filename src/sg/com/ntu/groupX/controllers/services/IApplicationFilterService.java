package sg.com.ntu.groupX.controllers.services;

import java.util.List;
import java.util.Map;

public interface IApplicationFilterService {
    public List<Application> filterByMaritalStatus(boolean married);
    public List<Application> filterByAge(int minAge, int maxAge);
    public List<Application> filterByFlatType(String flatType);
    public List<Application> filterByCompositeCriteria(Map criteria);
}
