package sg.com.ntu.group3.controllers;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import sg.com.ntu.group3.controllers.services.IApplicationFilterService;
import sg.com.ntu.group3.controllers.services.IReportService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Report;
import sg.com.ntu.group3.roles.HDBManager;
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


    public void generateReport(HDBManager manager) {
        Scanner input = new Scanner(System.in);

        // Get projects created by manager
        List<Project> managerProjects = Project.getProjectList().stream()
                .filter(p -> p.getCreatedBy().equalsIgnoreCase(manager.getName()))
                .toList();

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
