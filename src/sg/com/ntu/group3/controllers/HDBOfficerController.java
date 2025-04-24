package sg.com.ntu.group3.controllers;

import enums.ApplicationStatus;
import enums.RegistrationStatus;
import sg.com.ntu.group3.controllers.services.IManagerService;
import sg.com.ntu.group3.controllers.services.IOfficerService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.FlatType;
import sg.com.ntu.group3.models.Project;
import sg.com.ntu.group3.models.Registration;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.ApplicationView;
import sg.com.ntu.group3.views.ProjectView;
import sg.com.ntu.group3.views.RegistrationView;
import sg.com.ntu.group3.views.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** HDB Officer Controller class responsible for operations related to HDB Officers.
 * <p>This includes some overlapping methods with applicant users, but with additional checks and conditions.
 * Thus, this class inherits from ApplicationController, borrowing the methods for filtering projects.</p>
 *
 */
public class HDBOfficerController extends ApplicationController implements IOfficerService, IManagerService {

    public HDBOfficerController() {
        super();
    }

    /** Method for Officer registration to a desired project.
     * This method consolidates other methods that handle the smaller operations of creating a new registration and gathering user input.
     * @param officer The Officer making the registration
     */
    public void registerForProject(HDBOfficer officer) {
        List<Project> projectList = new ArrayList<>();
        for (Project project : Project.getProjectList()) {
            if (officer.canRegisterForProject(project)) {
                projectList.add(project);
            }
        }
        System.out.println("Enter the Name of the project you'd like to register for:");
        String projectRegName = ApplicationView.displayApplicationFormList(projectList);
        boolean success = Project.projectExists(projectRegName, projectList);
        if (success) {
            Project project = Project.findProject(projectRegName);
            if (officer.canRegisterForProject(project) && project.isWithinApplicationPeriod(new Date()) && project.isAvailableForRegistration()) {
                Registration registeredProject = new Registration(project, officer);
                officer.register(registeredProject);
                View.showOperationOutcome("Registration", true);
            } else {
                View.showOperationOutcome("Registration", false);
            }
        } else {
            View.showOperationOutcome("Registration", false);
        }

    }


    /** Implemented from IManagerService, this method is used to approve an officer's registration
     * Includes updating the registration status and assignment of the project to the officer
     * @param officer Officer whose registration is to be approved
     * @param registration The registration to be approved
     */
    public void approveOfficer(HDBOfficer officer, Registration registration) {
        Project project = registration.getProject();
        if (project.assignOfficer(officer)){
            officer.assignProject(registration.getProject());
            registration.approve();
            System.out.println("Officer approved for " + project.getName());
        }
        else{
            System.out.println("approval for officer failed");
        }


    }


    /** Method to initiate the approval or rejection of officer registration. Used by managers.
     * Includes method calls to approveOfficer and rejectOfficer, which handle the updating of statuses and values.
     * @param manager The HDB Manager making the approval or rejection request.
     */
    public void approveOfficerRegistration(HDBManager manager){
        if (!manager.hasActiveProject()) {
            View.showOperationOutcome("Operation", false);
            System.out.println("No currently active project!");
            return;
        }
        List<Registration> regList = Registration.findRegistrationsByProject(manager.getCurrentProject());
        List<Registration> pendingRegs = regList.stream()
                .filter(registration -> registration.getStatus() == RegistrationStatus.Pending)
                .toList();

        if (pendingRegs.isEmpty()) {
            System.out.println("No pending registrations");
            return;
        }

        Registration regToApprove = RegistrationView.ChoosePendingReg(pendingRegs);
        if (regToApprove != null) {
            int choice = RegistrationView.chooseApproveReject();
            switch (choice) {
                case 1:
                    approveOfficer(regToApprove.getOfficer(), regToApprove);
                    break;
                case 2:
                    rejectOfficer(regToApprove);
                default:
                    System.out.println("invalid selection");
                    break;
                }
            } else {
                System.out.println("invalid project");
        }
    }

    /** Method to reject an officer's registration.
     * Updates the status of the registration.
     * @param registration The registration to be rejected.
     */
    public void rejectOfficer(Registration registration) {
        registration.reject();
        System.out.println("Registration rejected " + registration.getProject().getName());
    }


    /** Method for HDB Officers to view the details of their already assigned project, after it is approved by the manager in charge.
     * Does not display anything if no project is found.
     * @param officer The HDB Officer making the request.
     */
    public void viewProjectDetails(HDBOfficer officer) {
        Project officerAssignedProject = officer.getAssignedProject();
        if (officerAssignedProject != null) {
            ProjectView.displayProjectInfo(officerAssignedProject);
        } else {
            System.out.println("No project assigned to the officer.");
        }
    }

    /** Implemented from IOfficerService, this method is responsible for viewing the status of outgoing registration
     * @param officer The HDB Officer making the request.
     */
    public void viewRegistrationStatus(HDBOfficer officer) {
        if (officer.getRegistrations().isEmpty()) {
            System.out.println("No Registrations");
        }
        for (Registration registration : officer.getRegistrations()) {
            System.out.println("Project: " + registration.getProject().getName() 
            + ", Status: " + registration.getStatus());
        }
    }

    /** Method for officers to update an applicant's flat booking selection.
     * <p>Used when an applicant has already requested a booking.
     * Includes calls to ApplicationView methods that display the relevant forms and gather user input to make the booking. </p>
     * @param officer The HDB Officer who is assigned the project with an application booking.
     */
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
        String number = ApplicationView.showBookingForm(applications);
        if (Integer.parseInt(number)>=0 && Integer.parseInt(number)<applications.size()) {
            Application application = applications.get(Integer.parseInt(number));
            Map<FlatType, Integer> availableUnitsToBook = application.getAvailableUnitsForApplicant();
            String booking = ApplicationView.displayBookingList(availableUnitsToBook); // saves name of the flat type to book
            if (application.getProject().checkForFlatType(booking)) {
                FlatType bookedFlat = FlatType.getTypeList().get(booking);
                application.approveBooking(bookedFlat); //update to being booked
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


    /** Method for HDB Officers to apply to a project as an applicant.
     * Similar to the method that normal applicants use to apply for projects, with added checks for officers.
     * @param officer The Officer making the application.
     */
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
                View.showOperationOutcome("Application", true);
            
            } else {
                View.showOperationOutcome("Application", false);
                System.out.println("Applicant has already submitted an application");
            }

        } else {
            View.showOperationOutcome("Application", false);
        }


    }

}
