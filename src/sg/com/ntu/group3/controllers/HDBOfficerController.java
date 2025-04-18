package sg.com.ntu.group3.controllers;

import enums.ApplicationStatus;
import enums.RegistrationStatus;
import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.ProjectView;
import sg.com.ntu.group3.views.View;

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
        List<Project> projectList = new ArrayList<>();
        for (Project project : Project.getProjectList()) {
            if (officer.canRegisterForProject(project)) {
                projectList.add(project);
            }
        }
        String applicationName = ApplicationView.displayApplicationFormList(projectList);
        boolean success = Project.projectExists(applicationName, projectList);
        if (success) {
            Project project = Project.findProject(applicationName);
            if (officer.canApplyForProject(project) && project.isWithinApplicationPeriod(project.getOpeningDate())&& project.isWithinApplicationPeriod(project.getCloseDate())) {
                Registration registeredProject = new Registration(project, officer);
                officer.getRegistrations().add(registeredProject);
                return true;
            }
        } else {
            View.showOperationOutcome("Application", success);
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
    public void approveOfficerRegistration(HDBManager manager){
    List<HDBOfficer> officers = manager.getOfficers();

    List<Registration> officerRegistrations = new ArrayList<>();
    for (HDBOfficer officer : officers) {
        officerRegistrations.addAll(officer.getRegistrations());
    }
    List<Registration> pendingRegs = officerRegistrations.stream()
            .filter(registration -> registration.getStatus() == RegistrationStatus.Pending)
            .toList();

    if (pendingRegs.isEmpty()) {
        System.out.println("No pending registrations");
        return;
    }

    Registration regToApprove = ApplicationView.ChoosePendingReg(pendingRegs);
    if (regToApprove != null) {
        int choice = ApplicationView.chooseApproveReject();
        switch (choice) {
            case 1:
                approveOfficer(regToApprove.getOfficer(), regToApprove);
                break;
            case 2:
                rejectOfficerRegistration(regToApprove);
            default:
                System.out.println("invalid selection");
                break;
            }
        } 
        else {
            System.out.println("invalid project");
        }
    }
    public void rejectOfficerRegistration(Registration registration) {
        registration.reject();
        System.out.println("Registration rejected " + registration.getProject().getName());
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
        if (officer.getAssignedProject()==null) {
            View.showOperationOutcome("Booking", false);
            System.out.println("No Project Assigned!");
            return;}
        List<Application> applications = officer.getAssignedProject().searchApplicationByStatus(ApplicationStatus.Booking);
        if (applications.isEmpty()) {
            View.showOperationOutcome("Booking", false);
            System.out.println("No approved applications!");
            return;
        }
        String nric = ApplicationView.showBookingForm(applications);
        if (authenticationService.validateNRIC(nric)) {
            Application application = applicationFilterService.filterByNRIC(applications,nric);
            Map<FlatType, Integer> availableUnitsToBook = application.getAvailableUnitsForApplicant();
            String booking = ApplicationView.displayBookingList(availableUnitsToBook); // saves name of the flat type to book
            if (application.getProject().checkForFlatType(booking)) {
                application.setStatus(ApplicationStatus.Booked); //update to being booked
                View.showOperationOutcome("Booking", true);
            } else {
                View.showOperationOutcome("Booking", false);
                System.out.println("Invalid Input!"); //unsuccessful: invalid flat type entered from view class
            }
        } else {
            View.showOperationOutcome("Booking", false);
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
        List<Project> projectList = findAvailableProjects(
                findVisibleProjects(findEligibleProjects(officer))
                , officer);
        String applicationName = ApplicationView.displayApplicationFormList(projectList);
        boolean success = Project.projectExists(applicationName, projectList);
        if (success) {
            Project project = Project.findProject(applicationName);
            if (officer.canApplyForProject(project)) {
                Application newApplication = new Application(officer, Project.findProject(applicationName));
                officer.setApplication(newApplication);
                View.showOperationOutcome("Application", success);
            
            } else {
                View.showOperationOutcome("Application", false);
                System.out.println("Applicant has already submitted an application");
            }

        } else {
            View.showOperationOutcome("Application", success);
        }


    }

}
