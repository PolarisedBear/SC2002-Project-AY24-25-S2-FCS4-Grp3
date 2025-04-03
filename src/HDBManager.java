import enums.ApplicationStatus;

import java.util.ArrayList;
import java.util.List;

public class HDBManager extends User{
    private List<Project> createdProjects = new ArrayList<>();
    private Project currentProject;

    public HDBManager() {
    }

    public void createProject() {
        Project newProject = new Project();
        this.createdProjects.add(newProject);
    }

    public void editProject(Project project) {

    }

    public boolean deleteProject(Project project) {
        return this.createdProjects.remove(project);
    }

    public void toggleProjectVisibility(Project project, boolean isVisible) {

    }

    public void viewAllProjects() {
        List<Project> projectList = ProjectRegistry.getAllProjects();
        for (Project project : projectList) {
            System.out.println(project.toString());
        }
    }

    public void viewAllSelfProjects() {
        for (Project project : this.createdProjects) {
            System.out.println(project.toString());
        }
    }

    public void viewAllOfficerRegistration() {

    }

    public void approveOfficerRegistration(Registration registration) {
        registration.approve();
    }

    public void rejectOfficerRegistration(Registration registration) {
        registration.reject();
    }

    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.Successful);
    }

    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.Unsuccessful);
    }

    public void approveWithdrawal(Application application) {

    }

    public void rejectWithdrawal(Application application) {

    }

    public void generateReport(int number) {
        Report report = new Report();
    }

    public void viewEnquiries() {

    }

    public void replyEnquiries(Enquiry enquiry, String reply) {
        enquiry.reply(reply);
    }


}
