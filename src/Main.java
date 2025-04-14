//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import enums.Role;
import sg.com.ntu.group3.controllers.*;
import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.views.AuthView;
import sg.com.ntu.group3.views.SessionView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // initialise app and services
        Session session = new Session();
        AuthenticationService authenticationService = new AuthenticationService(session);
        UserController userController = new UserController(session, authenticationService);
        ApplicationController applicationController = new ApplicationController(session);
        ApplicationFilterService applicationFilterService = new ApplicationFilterService(session);
        ProjectController projectController = new ProjectController();
        EnquiryController enquiryController = new EnquiryController();
        HDBOfficerController hdbOfficerController = new HDBOfficerController(session, authenticationService, applicationFilterService);
        ReportController reportController = new ReportController();
        WithdrawalController withdrawalController = new WithdrawalController();


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
                                applicationController.applyForProject();
                                break;
                            case 6://view applied project
                                applicationController.viewApplication(currentUser);
                                break;
                            case 7://book flat and send to attached officer
                                Boolean booking = applicationController.requestFlatBooking(currentUser);
                                break;
                            case 8://request withdrawal
                                withdrawalController.submitWithdrawalRequest(currentUser);
                                break;
                            case 9://submit enquiry
                            case 10://view, edit, delete enquiry

                        }
                    }

                    if (session.getRole()==Role.OFFICER) {
                        HDBOfficer currentUser = session.getCurrentHDBOfficer();
                        int choice = SessionView.showMainMenuHDBOfficer(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2://change password
                            case 3://update user info
                            case 4://view user profile
                            case 5://
                            case 6://
                            case 7://
                            case 8://
                            case 9://
                            case 10://
                            case 11://
                            case 12://
                            case 13://
                            case 14://
                            case 15://
                            case 16://

                        }
                    }

                    if (session.getRole()==Role.MANAGER) {
                        HDBManager currentUser = session.getCurrentHDBManager();
                        int choice = SessionView.showMainMenuHDBManager(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2://change password
                            case 3://update user info
                            case 4://view user profile
                            case 5://
                            case 6://
                            case 7://
                            case 8://
                            case 9://
                            case 10://

                        }
                    }
                }
            }
        }

    }


}