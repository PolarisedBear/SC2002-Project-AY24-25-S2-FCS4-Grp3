package sg.com.ntu.group3.controllers;
import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.services.IApplicationService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.roles.User;
import sg.com.ntu.group3.views.ApplicationView;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController extends ApplicationView implements IApplicationService {
    private Session session;

    public ApplicationController(Session session) {
        this.session = session;
    }

    private boolean validateOfficerEligibility(HDBOfficer officer, Project project) {
        return false;
    }

    public void applyForProject() {
        Applicant applicant = (Applicant) this.session.getCurrentUser();
        if (applicant.getApplication()!=null) {
            List<Project> projectList = findAvailableProjects(
                    findVisibleProjects(findEligibleProjects(applicant))
                    , applicant);
            String applicationName = ApplicationView.displayApplicationFormList(projectList);
            boolean success = Project.projectExists(applicationName, projectList);
            if (success) {
                Application newApplication = new Application(applicant, Project.findProject(applicationName));
                applicant.setApplication(newApplication);
                ApplicationView.showOperationOutcome("Application", success);
            } else {
                ApplicationView.showOperationOutcome("Application", success);
            }
        } else {
            ApplicationView.showOperationOutcome("Application", false);
            System.out.println("Applicant has already submitted an application");
        }


    }

    public void withdrawApplication() {

    }

    public void approveApplication() {

    }

    public void bookFlat() {

    }

    public void getApplicationStatus() {

    }

    public List<Project> findEligibleProjects(Applicant applicant) {
        List<Project> eligibleProjects = new ArrayList<>();
        for (Project project : Project.getProjectList()) {
            if (project.isEligibleForApplication(applicant)) {
                eligibleProjects.add(project);
            }
        }
        return eligibleProjects;
    }

    public List<Project> findVisibleProjects(List<Project> eligibleProjects) {
        List<Project> visibleProjects = new ArrayList<>();
        for (Project project : eligibleProjects) {
            if (project.isVisible()) {
                visibleProjects.add(project);
            }
        }
        return visibleProjects;
    }

    public List<Project> findAvailableProjects(List<Project> visibleProjects, Applicant applicant) {
        List<Project> availableProjects = new ArrayList<>();
        for (Project project : visibleProjects) {
            if (project.hasAvailableUnitsForApplicant(applicant)) {
                availableProjects.add(project);
            }
        }
        return availableProjects;
    }


    @Override
    public void withdrawApplication(Application application) {

    }

    @Override
    public ApplicationStatus getApplicationStatus(Application application) {
        return null;
    }

    @Override
    public boolean bookFlat(Application application, FlatType flatType) {
        return false;
    }

    public static boolean hasExistingBooking(Applicant applicant) {
        if (applicant.getApplication() != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void applyForProject(Applicant applicant, Project project, FlatType flatType) {
            Application newApplication = new Application(applicant, project);
            applicant.setApplication(newApplication);
            project.addApplication(newApplication);
    }
}
