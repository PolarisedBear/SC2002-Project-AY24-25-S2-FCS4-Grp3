//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import sg.com.ntu.group3.controllers.Session;
import sg.com.ntu.group3.controllers.UserController;
import sg.com.ntu.group3.controllers.services.AuthenticationService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // initialise app
        Session session = new Session();
        AuthenticationService authenticationService = new AuthenticationService(session);
        UserController userController = new UserController(session, authenticationService);


        //register users
        authenticationService.registerFromExcel();

        //Login, and gain user permissions
        boolean login = userController.login();
        session.gainAccess();

        // Start app functions
        while(session.curLoggedIn()) {









        }



    }
}