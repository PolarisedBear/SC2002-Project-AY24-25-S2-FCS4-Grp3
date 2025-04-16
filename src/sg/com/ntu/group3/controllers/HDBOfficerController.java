package sg.com.ntu.group3.controllers;

import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.ProjectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HDBOfficerController implements IOfficerService, IManagerService {
    private Session session;
    private AuthenticationService authenticationService;
    private ApplicationFilterService applicationFilterService;

    public HDBOfficerController(Session session, AuthenticationService authenticationService, ApplicationFilterService applicationFilterService) {
        this.session = session;
        this.authenticationService = authenticationService;
        this.applicationFilterService = applicationFilterService;
    }

    public boolean registerForProject(HDBOfficer officer) {
        Project project = officer.getAssignedProject();
        if (officer.canApplyforproject(project) && project.isWithinApplicationPeriod(null)) {
            //notcompleted below logic needs to be approved by manager before can be added
            Registration registeredProject = new Registration(project);
            officer.getRegistrations().add(registeredProject);
            return true;
        }
        return false;
    }


    @Override
    public String getRegistrationStatus(HDBOfficer officer, Project project) {
        return "";
    }

    public void approveOfficer(HDBOfficer officer, Registration registration) {
        Project project = registration.getProject();
        if (project.assignOfficer(officer)){
            officer.assignProject(registration.getProject());
            project.assignOfficer(officer);
            registration.approve();
            System.out.println("Officer approved for " + project.getName());
        }
        else{
            System.out.println("approval for officer failed");
        }


    }


    public void viewProjectDetails(HDBOfficer officer) {
        Project officerAssignedProject = officer.getAssignedProject();
        if (officerAssignedProject != null) {
            ProjectView.displayProjectInfo(officerAssignedProject);
        } else {
            System.out.println("No project assigned to the officer.");
        }
    }
    public void viewRegistrationStatus(HDBOfficer officer) {
        for (Registration registration : officer.getRegistrations()) {
            System.out.println("Project: " + registration.getProject().getName() 
            + ", Status: " + registration.getStatus());
        }
    }

    public void bookFlat(HDBOfficer officer) {
        List<Application> applications = officer.getAssignedProject().searchApplicationByStatus(ApplicationStatus.Booking);
        String nric = ApplicationView.showBookingForm(applications);
        if (authenticationService.validateNRIC(nric)) {
            Application application = applicationFilterService.filterByNRIC(applications,nric);
            Map<FlatType, Integer> availableUnitsToBook = application.getAvailableUnitsForApplicant();
            String booking = ApplicationView.displayBookingList(availableUnitsToBook); // saves name of the flat type to book
            if (application.getProject().checkForFlatType(booking)) {
                application.setStatus(ApplicationStatus.Booked); //update to being booked
                ApplicationView.showOperationOutcome("Booking", true);
            } else {
                ApplicationView.showOperationOutcome("Booking", false);
                System.out.println("Invalid Input!"); //unsuccessful: invalid flat type entered from view class
            }
        } else {
            ApplicationView.showOperationOutcome("Booking", false);
            System.out.println("Invalid Input!");
        }
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

    public void officerApplyForProject(HDBOfficer officer) {
        if (officer.canApplyForProject()) {
            List<Project> projectList = findAvailableProjects(
                    findVisibleProjects(findEligibleProjects(officer))
                    , officer);
            String applicationName = ApplicationView.displayApplicationFormList(projectList);
            boolean success = Project.projectExists(applicationName, projectList);
            if (success) {
                Application newApplication = new Application(officer, Project.findProject(applicationName));
                officer.setApplication(newApplication);
                ApplicationView.showOperationOutcome("Application", success);
            } else {
                ApplicationView.showOperationOutcome("Application", success);
            }
        } else {
            ApplicationView.showOperationOutcome("Application", false);
            System.out.println("Applicant has already submitted an application");
        }

    }

}
