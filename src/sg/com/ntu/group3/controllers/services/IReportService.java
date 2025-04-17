package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;

import java.util.List;
import java.util.Map;

public interface IReportService {
    public List<Application> generateReport(Map criteria);
    public Map<String, String> generateReceipt(Application application);
}
