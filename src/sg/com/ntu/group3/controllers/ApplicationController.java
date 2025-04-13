package sg.com.ntu.group3.controllers;
import enums.ApplicationStatus;
import sg.com.ntu.group3.controllers.services.IApplicationService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.views.ApplicationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        if (applicant.getApplication()!=null
                || applicant.getApplication().getStatus()==ApplicationStatus.Unsuccessful
                || applicant.getApplication().getStatus()==ApplicationStatus.Withdrawn) {
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
            ApplicationView.showOperationOutcome("Display", false);
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
        return application.getStatus();
    }

    @Override
    public Boolean requestFlatBooking(Applicant applicant) {
        if (applicant.canBookFlat()) {
            applicant.getApplication().setStatus(ApplicationStatus.Booking);
            ApplicationView.showOperationOutcome("Request for Booking", true);
            return true;
        } else {
            ApplicationView.showOperationOutcome("Request for Booking", false);
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

    public void applyForProject(Applicant applicant, Project project, FlatType flatType) {
            Application newApplication = new Application(applicant, project);
            applicant.setApplication(newApplication);
            project.addApplication(newApplication);
    }


}
