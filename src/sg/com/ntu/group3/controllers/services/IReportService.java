package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.roles.HDBManager;

import java.util.List;
import java.util.Map;

/** Report Service interface including methods to generate reports and receipts.
 * Implemented by ReportController.
 *
 */
public interface IReportService {
    void generateReport(HDBManager manager);
    Map<String, String> generateReceipt(Application application);
}
