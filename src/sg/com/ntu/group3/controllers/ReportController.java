package sg.com.ntu.group3.controllers;
import java.util.*;


import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.IReportService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Report;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.ReportView;
import sg.com.ntu.group3.views.View;

/** Report Controller Class responsible for generating reports and receipts
 *
 */
public class ReportController implements View, IReportService {
    private ApplicationFilterService applicationFilterService;
    private Scanner input;

    public ReportController(ApplicationFilterService applicationFilterService) {
        this.applicationFilterService = applicationFilterService;
        this.input = new Scanner(System.in);
    }


    /** Method for generating the receipt for an applicant's application
     * @param officer The Officer making the request
     */
    public void generateReceiptForm(HDBOfficer officer) {
        String nric = ReportView.displayReceiptOptions(officer);
        //check if the name input matches that of the applicants
        if (officer.getAssignedProject().getApplications()
                .stream().anyMatch(appl -> appl.getApplicant().getNric().equalsIgnoreCase(nric))) {
            Application application = applicationFilterService.filterByNRIC(nric, officer.getAssignedProject().getApplications());
            Map<String, String> receiptDetails = generateReceipt(application);
            ReportView.displayReceipt(receiptDetails);
        } else {
            View.showOperationOutcome("Receipt Generation", false);
            System.out.println("No applicant found!");
        }
    }


    /** Method for collecting the details to be printed on the receipt for a given application. Used in tandem with generateReceiptForm
     * @param application The application whose details will be printed
     * @return A Map of each attribute to its value
     */
    @Override
    public Map<String, String> generateReceipt(Application application) {
        Applicant applicant = application.getApplicant();
        Project project = application.getProject();
        FlatType flat = application.getBookedFlat();
        String name = applicant.getName();
        String nric = applicant.getNric();
        String age = String.valueOf(applicant.getAge());
        String maritalStatus = applicant.getMaritalStatus();
        String projName = project.getName();
        String projOpenDate = project.getOpeningDate().toString();
        String projCloseDate = project.getCloseDate().toString();
        String bookingStatus = application.getStatus().toString();
        String bookedFlat;
        if (flat!=null) {
            bookedFlat = flat.getType();
        } else {
            bookedFlat = "None";
        }
        return Map.of("name", name, "nric", nric, "age", age, "maritalStatus", maritalStatus,
                "projName", projName, "projOpenDate", projOpenDate, "projCloseDate", projCloseDate,
                "bookingStatus", bookingStatus, "bookedFlat", bookedFlat);
    }

    /** Method for managers to generate a report, including application filters
     * @param manager The Manager making the request
     */
    @Override
    public void generateReport(HDBManager manager) {
        Scanner input = new Scanner(System.in);

        // Get projects created by manager
        List<Project> managerProjects = manager.getCreatedProjects();

        if (managerProjects.isEmpty()) {
            System.out.println("You have not created any projects.");
            return;
        }

        // Ask user to select project
        int projectChoice = ReportView.generateReportForm(managerProjects);

        if (projectChoice < 0 || projectChoice >= managerProjects.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        // ask for filter mode
        Project selectedProject = managerProjects.get(projectChoice);
        List<Application> applicationList = selectedProject.getApplications();
        int filter = ReportView.reportFormFilterQuery();
        List<Applicant> applicantList = new ArrayList<>();
        switch (filter) {
            case 1:
                boolean isMarried = ReportView.maritalStatusFilterQuery();
                applicationList = applicationFilterService.filterByMaritalStatus(isMarried, applicationList);
                break;
            case 2:
                Map.Entry<Integer, Integer> values = ReportView.ageFilterQuery();
                applicationList = applicationFilterService.filterByAge(values.getKey(), values.getValue(), applicationList);
                break;
            case 3:
                String type = ReportView.flatTypeFilterQuery();
                applicationList = applicationFilterService.filterByFlatType(type, applicationList);
                break;
            default:
                break;

        }
        for (Application application : applicationList) {
            applicantList.add(application.getApplicant());
        }

        // Get number of applicants
        int count = ReportView.reportFormApplicantQuery();
        if (count<1 || count>applicantList.size()) {
            View.showOperationOutcome("Report Generation", false);
            System.out.println("Invalid range");
            return;
        }

        Report report = new Report();
        report.generateApplicantBookingReport(count, applicantList);

    }

}
