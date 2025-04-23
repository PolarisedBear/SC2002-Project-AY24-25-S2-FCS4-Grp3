package sg.com.ntu.group3.controllers;
import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.services.IApplicationService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.View;

import java.util.*;

/** Application Controller Class responsible for handling operations on application objects from different types of users.
 * <p>Applicant: applying for a new project, and requesting a flat </p>
 * <p>Manager: approving and rejecting applications </p>
 */
public class ApplicationController extends ApplicationView implements IApplicationService {
    private Session session;

    public ApplicationController(Session session) {
        this.session = session;
    }

    private boolean validateOfficerEligibility(HDBOfficer officer, Project project) {
        return false;
    }

    /** Method for applicants to apply for a project. If successful, a new Application is created
     * @param applicant The Applicant whose new application will be linked to.
     */
    public void applyForProject(Applicant applicant) {
        if (applicant.canApplyForProject()) {
            List<Project> projectList = findAvailableProjects(
                    findVisibleProjects(findEligibleProjects(applicant))
                    , applicant);
            String applicationName = ApplicationView.displayApplicationFormList(projectList);
            boolean success = Project.projectExists(applicationName, projectList);
            if (success) {
                Application newApplication = new Application(applicant, Project.findProject(applicationName));
                applicant.setApplication(newApplication);
                View.showOperationOutcome("Application", success);
            } else {
                View.showOperationOutcome("Application", success);
            }
        } else {
            View.showOperationOutcome("Application", false);
            System.out.println("Applicant has already submitted an application");
        }


    }

    /** Method for managers to approve a selected application. Involves modifying statuses and attributes from the application
     * @param application The application object to be approved
     */
    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.Successful);
        Applicant applicant = application.getApplicant();
        Project project = application.getProject();
        project.addApplicant(applicant);
    }

    /** Method for managers to reject a selected application. Involves modifying attributes in the application
     * @param application The application object to be rejected.
     */
    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.Unsuccessful);
    }

    /** Method for applicants to view their current application. Prints an error message if no application was found
     * @param applicant The applicant whose application is to be viewed
     */
    public void viewApplication(Applicant applicant) {
        if (applicant.getApplication()!=null) {
            ApplicationView.displayApplication(applicant.getApplication());
        } else {
            View.showOperationOutcome("Display", false);
            System.out.println("No application found!");
        }
    }


    /** Method to find all eligible projects for an applicant to apply to.
     * Only searches by checking the eligibility rules of each project, not availability
     * @param applicant The applicant whose eligibility is checked
     * @return a list of projects which the applicant is eligible to apply to
     */
    public List<Project> findEligibleProjects(Applicant applicant) {
        List<Project> eligibleProjects = new ArrayList<>();
        for (Project project : Project.getProjectList()) {
            if (project.isEligibleForApplication(applicant)) {
                eligibleProjects.add(project);
            }
        }
        return eligibleProjects;
    }

    /** Method to find all visible projects from a list of eligible projects for an applicant. Used in tandem with findEligibleProjects
     * @param eligibleProjects The list of eligible projects to search from
     * @return a list of projects with their visibility attribute set to true
     */
    public List<Project> findVisibleProjects(List<Project> eligibleProjects) {
        List<Project> visibleProjects = new ArrayList<>();
        for (Project project : eligibleProjects) {
            if (project.isVisible()) {
                visibleProjects.add(project);
            }
        }
        return visibleProjects;
    }

    /** Method to check the availability of flats in projects visible to an applicant. Used in tandem with findVisibleProjects
     * @param visibleProjects The list of visible projects
     * @param applicant The applicant who is being considered
     * @return the list of projects with available flats for the applicant to request a booking
     */
    public List<Project> findAvailableProjects(List<Project> visibleProjects, Applicant applicant) {
        Date currentDate = new Date();
        List<Project> availableProjects = new ArrayList<>();
        for (Project project : visibleProjects) {
            if (project.hasAvailableUnitsForApplicant(applicant) && project.isWithinApplicationPeriod(currentDate)) {
                availableProjects.add(project);
            }
        }
        return availableProjects;
    }


    /** Method for retrieving the status of an application from the controller
     * @param application Application whose status is to be retrieved
     * @return The status of the application
     */
    @Override
    public ApplicationStatus getApplicationStatus(Application application) {
        ApplicationStatus appStatus = application.getStatus();
        ApplicationView.displayApplicationStatus(application);
        return appStatus;
    }

    /** Method for an applicant to request a flat after the application was approved. Modifies the status of their application
     * @param applicant The applicant requesting the booking
     * @return A boolean result true if the conditions for the request are met, false otherwise.
     */
    @Override
    public Boolean requestFlatBooking(Applicant applicant) {
        if (applicant.canBookFlat()) {
            applicant.getApplication().setStatus(ApplicationStatus.Booking);
            View.showOperationOutcome("Request for Booking", true);
            return true;
        } else {
            View.showOperationOutcome("Request for Booking", false);
        }
        return false;
    }


    /** Method for managers to decide whether to approve and reject applications.
     * @param manager The manager making the review request
     */
    public void reviewApplications(HDBManager manager) {
        if (!manager.hasActiveProject()) {
            View.showOperationOutcome("Application Retrieval", false);
            System.out.println("Currently not handling any projects!");
            return;
        }
        List<Application> pendingApps = new ArrayList<>();
        for (Application app : manager.getCurrentProject().getApplications()) {
            if (app.getStatus() == ApplicationStatus.Pending &&
                    app.getProject().getCreatedBy().equalsIgnoreCase(manager.getName())) {
                pendingApps.add(app);
            }
        }

        if (pendingApps.isEmpty()) {
            System.out.println("No pending applications found for your projects.");
            return;
        }
        int selection = ApplicationView.displayPendingApplications(pendingApps);
        if (selection >= 0 && selection < pendingApps.size()) {
            Application selectedApp = pendingApps.get(selection);
            int action = ApplicationView.promptApproveOrReject(selectedApp);
            switch (action) {
                case 1 -> approveApplication(selectedApp);
                case 2 -> rejectApplication(selectedApp);
                default -> System.out.println("No action taken.");
            }
        } else {
            System.out.println("Invalid selection.");
        }

    }
}
