package sg.com.ntu.group3.controllers.services;

import java.util.List;
import java.util.Map;

public interface IReportService {
    public List<Application> generateReport(Map criteria);
    public String generateReceipt(Application application);
}
