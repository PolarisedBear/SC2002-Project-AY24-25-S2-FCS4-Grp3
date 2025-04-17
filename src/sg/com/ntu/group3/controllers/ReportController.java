package sg.com.ntu.group3.controllers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


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

public class ReportController implements View, IReportService {
    private ApplicationFilterService applicationFilterService;

    public ReportController(ApplicationFilterService applicationFilterService) {
        this.applicationFilterService = applicationFilterService;
    }


    public void generateReport() {

    }


    public void generateReceiptForm(HDBOfficer officer) {
        String nric = ReportView.displayReceiptOptions(officer);
        //check if the name input matches that of the applicants
        if (officer.getAssignedProject().getApplications()
                .stream().anyMatch(appl -> appl.getApplicant().getNric().equalsIgnoreCase(nric))) {
            Application application = applicationFilterService.filterByNRIC(officer.getAssignedProject().getApplications(), nric);
            Map<String, String> receiptDetails = generateReceipt(application);
            ReportView.displayReceipt(receiptDetails);
        } else {
            View.showOperationOutcome("Receipt Generation", false);
            System.out.println("No applicant found!");
        }
    }


    @Override
    public List<Application> generateReport(Map criteria) {
        return List.of();
    }

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


    public void generateReport(HDBManager manager) {
        Scanner input = new Scanner(System.in);

        // Get projects created by manager
        List<Project> managerProjects = manager.getCreatedProjects();

        if (managerProjects.isEmpty()) {
            System.out.println("You have not created any projects.");
            return;
        }

        // Ask user to select project
        System.out.println("\n--- Your Projects ---");
        for (int i = 0; i < managerProjects.size(); i++) {
            System.out.println("[" + i + "] " + managerProjects.get(i).getName());
        }

        System.out.print("Select a project to generate report for: ");
        int projectChoice = input.nextInt();
        input.nextLine();

        if (projectChoice < 0 || projectChoice >= managerProjects.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Project selectedProject = managerProjects.get(projectChoice);

        // Get number of applicants
        System.out.print("Enter number of applicants to include in the report: ");
        int count = input.nextInt();
        input.nextLine();

        Report report = new Report();
        report.generateApplicantBookingReport(count, selectedProject.getApplicants());
    }
}
