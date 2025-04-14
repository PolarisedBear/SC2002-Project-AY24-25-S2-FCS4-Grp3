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

    public boolean registerForProject(HDBOfficer officer, Project project) {

            if (officer.canApplyforproject(project) && project.isWithinApplicationPeriod(project.getCloseDate())) {
                Registration newRegistration = new Registration(project);
                officer.getRegistrations().add(newRegistration);
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
        Project project = officer.getAssignedProject();
        if (project != null) {
            System.out.println("Project Name: " + project.getName() +
            "units avail: " + project.getUnitsAvailable() + 
            "Project Opening Date: " + project.getOpeningDate() +
            "Project Closing Date: " + project.getCloseDate() +
            "Flat Types: " + project.getFlatTypes());

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

}
