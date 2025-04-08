import java.util.List;
import java.util.Map;

public class ReportController implements ReportView, IApplicationFilterService{
    private IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
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
}
