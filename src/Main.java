//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import enums.Role;
import sg.com.ntu.group3.controllers.*;
import sg.com.ntu.group3.controllers.services.ApplicationFilterService;
import sg.com.ntu.group3.controllers.services.AuthenticationService;
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
        HDBOfficerController hdbOfficerController = new HDBOfficerController(session);
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
                    if (session.getRole()== Role.APPLICANT) {
                        int choice = SessionView.showMainMenuApplicant(session);
                        switch (choice) {
                            case 1: session.logout(); break; //logout
                            case 2: //change password

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

                    if (session.getRole()==Role.OFFICER) {
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