//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import enums.Role;
import sg.com.ntu.group3.controllers.*;
import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.models.*;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.AuthView;
import sg.com.ntu.group3.views.SessionView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        // initialise app and services
        Session session = new Session();
        AuthenticationService authenticationService = new AuthenticationService(session);
        UserController userController = new UserController(session, authenticationService);
        ApplicationController applicationController = new ApplicationController(session);
        ApplicationFilterService applicationFilterService = new ApplicationFilterService(session);
        ProjectController projectController = new ProjectController();
        EnquiryController enquiryController = new EnquiryController(session);
        HDBOfficerController hdbOfficerController = new HDBOfficerController(session, authenticationService, applicationFilterService);
        ReportController reportController = new ReportController();
        WithdrawalController withdrawalController = new WithdrawalController();

        //For testing
        AgeOnlyEligibilityRule atLeast35 = new AgeOnlyEligibilityRule(35);
        AgeAndMaritalStatusEligibilityRule atLeast21andMaried = new AgeAndMaritalStatusEligibilityRule(true, 21);
        List<EligibilityRule> two_room = List.of(atLeast35, atLeast21andMaried);
        List<EligibilityRule> three_room = List.of(atLeast21andMaried);
        FlatType TwoR = new FlatType(two_room, 2, "2_room");
        FlatType ThreeR = new FlatType(three_room, 3, "3_room");
        new Project("Acacia Breeze", List.of(TwoR, ThreeR), "Yishun", new Date(2026, 12, 25), true, 5, Map.of(TwoR, 2, ThreeR, 3));




        //register users
        authenticationService.registerFromExcel();
        //Start app
        while (true) {
            int isRunning = AuthView.welcomeScreen();
            if (isRunning==0) {break;} //quit and close program
            if (isRunning==1) {
                //Login, and gain user permissions
                boolean login = userController.login();
                session.gainAccess();
                // Start app functions
                while(session.curLoggedIn()) { // Check roles, print menus accordingly
                    if (session.getRole()==Role.APPLICANT) {
                        Applicant currentUser = session.getCurrentApplicant();
                        int choice = SessionView.showMainMenuApplicant(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2: //change password
                                boolean passwordChange = userController.changePassword();
                                break;
                            case 3://update user info
                                userController.updateUserInfo();
                                break;
                            case 4://view user profile
                                userController.displayUserInfo();
                                break;
                            case 5://apply for project
                                applicationController.applyForProject(currentUser);
                                break;
                            case 6://view applied project
                                applicationController.viewApplication(currentUser);
                                break;
                            case 7://request booking
                                Boolean booking = applicationController.requestFlatBooking(currentUser);
                                break;
                            case 8://request withdrawal
                                withdrawalController.submitWithdrawalRequest(currentUser);
                                break;
                            case 9://submit enquiry
                                enquiryController.newEnquirySubmission(currentUser);
                                break;
                            case 10://view, edit, delete enquiry
                                enquiryController.editReplyAndDelete(currentUser);
                                break;

                        }
                    }

                    if (session.getRole()==Role.OFFICER) {
                        HDBOfficer currentUser = session.getCurrentHDBOfficer();
                        int choice = SessionView.showMainMenuHDBOfficer(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2: //change password
                                boolean passwordChange = userController.changePassword();
                                break;
                            case 3://update user info
                                userController.updateUserInfo();
                                break;
                            case 4://view user profile
                                userController.displayUserInfo();
                                break;
                            case 5://apply for project (cannot apply for registered project)
                                hdbOfficerController.officerApplyForProject(currentUser);
                                break;
                            case 6://view applied project
                                hdbOfficerController.viewProjectDetails(currentUser);
                                break;
                            case 7://request booking
                            case 8://request withdrawal
                            case 9://submit enquiry
                            case 10://view, edit and delete
                            case 11://register or join project
                                hdbOfficerController.registerForProject(currentUser);
                                break;
                            case 12://view registration status
                            case 13://view project details
                            case 14://Update applicant flat selection
                                hdbOfficerController.bookFlat(currentUser);
                                break;
                            case 15://view and reply to enquiries
                            case 16://generate applicant's flat booking request

                        }
                    }

                    if (session.getRole()==Role.MANAGER) {
                        HDBManager currentUser = session.getCurrentHDBManager();
                        int choice = SessionView.showMainMenuHDBManager(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2: //change password
                                boolean passwordChange = userController.changePassword();
                                break;
                            case 3://update user info
                                userController.updateUserInfo();
                                break;
                            case 4://view user profile
                                userController.displayUserInfo();
                                break;
                            case 5://create project
                                projectController.createProject(currentUser);
                                break;
                            case 6://view projects
                                projectController.viewProjects(currentUser);
                                break;
                            case 7://approve/reject application
                                applicationController.reviewApplications(currentUser);
                            case 8://approve/reject withdrawal
                                withdrawalController.reviewWithdrawalRequests(currentUser);
                                break;
                            case 9://generate report
                                reportController.generateReport(currentUser);
                                break;
                            case 10://view enquiries
                                enquiryController.viewAndReplyToEnquiries(currentUser);
                                break;
                        }
                    }
                }
            }
        }
    }
}