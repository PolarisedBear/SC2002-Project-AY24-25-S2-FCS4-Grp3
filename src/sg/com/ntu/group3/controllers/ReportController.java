package sg.com.ntu.group3.controllers;
import java.util.List;
import java.util.Map;


import sg.com.ntu.group3.controllers.services.IApplicationFilterService;
import sg.com.ntu.group3.controllers.services.IReportService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.views.ReportView;

public class ReportController implements ReportView, IApplicationFilterService, IReportService {


    public ReportController() {
    }

    @Override
    public void displayReportOptions() {

    }

    public void generateReport() {

    }

    @Override
    public void displayReceipt() {

    }

    public void generateReceipt() {

    }

    public List filterApplications(Map criteria) {

    }

    @Override
    public List<Application> filterByMaritalStatus(boolean married) {
        return List.of();
    }

    @Override
    public List<Application> filterByAge(int minAge, int maxAge) {
        return List.of();
    }

    @Override
    public List<Application> filterByFlatType(String flatType) {
        return List.of();
    }

    @Override
    public List<Application> filterByCompositeCriteria(Map criteria) {
        return List.of();
    }

    @Override
    public List<Application> generateReport(Map criteria) {
        return List.of();
    }

    @Override
    public String generateReceipt(Application application) {
        return "";
    }
}
