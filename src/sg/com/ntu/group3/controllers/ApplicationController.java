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

public class ApplicationController extends ApplicationView implements IApplicationService {
    private Session session;

    public ApplicationController(Session session) {
        this.session = session;
    }

    private boolean validateOfficerEligibility(HDBOfficer officer, Project project) {
        return false;
    }

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

    public void approveApplication(Application application) {
        application.setStatus(ApplicationStatus.Successful);
    }
    public void rejectApplication(Application application) {
        application.setStatus(ApplicationStatus.Unsuccessful);
    }

    public void viewApplication(Applicant applicant) {
        if (applicant.getApplication()!=null) {
            ApplicationView.displayApplication(applicant.getApplication());
        } else {
            View.showOperationOutcome("Display", false);
            System.out.println("No application found!");
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
        Date currentDate = new Date();
        List<Project> availableProjects = new ArrayList<>();
        for (Project project : visibleProjects) {
            if (project.hasAvailableUnitsForApplicant(applicant) && project.isWithinApplicationPeriod(currentDate)) {
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
        ApplicationStatus appStatus = application.getStatus();
        ApplicationView.displayApplicationStatus(application);
        return appStatus;
    }

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

//    public Application bookFlat(Applicant applicant) { //to route to HDBOfficer.java for approval
//        if (applicant.getApplication()==null) {
//            ApplicationView.showOperationOutcome("Booking", false);
//            System.out.println("No application found!");
//            return null;}
//
//        Application application = applicant.getApplication(); //application to be modified
//
//        if (application.getStatus() == ApplicationStatus.Successful) {
//            Map<FlatType, Integer> availableUnitsToBook = application.getAvailableUnitsForApplicant(); // get all the available flats and the number available
//            String booking = ApplicationView.displayBookingList(availableUnitsToBook); // saves name of the flat type to book
//            if (application.getProject().checkForFlatType(booking)) {
//                application.setStatus(ApplicationStatus.Booking); //update to being booked (booking)
//                ApplicationView.showOperationOutcome("Booking", true);
//                return application; //successful
//            } else {
//                ApplicationView.showOperationOutcome("Booking", false);
//                System.out.println("Invalid Input!"); //unsuccessful: invalid flat type entered from view class
//            }
//        } else if (application.getStatus() == ApplicationStatus.Booking) {
//            ApplicationView.showOperationOutcome("Booking",false);
//            System.out.println("Application already has previous booking"); //unsuccessful: application has previously been booked
//        } else {
//            ApplicationView.showOperationOutcome("Booking",false);
//            System.out.println("Application not yet approved"); //unsuccessful: application not approved
//        }
//        return null;
//    }

    public boolean hasExistingBooking(Applicant applicant) {
        if (applicant.getApplication().getStatus() == ApplicationStatus.Booking) {
            return true;
        } else {
            return false;
        }
    }


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
