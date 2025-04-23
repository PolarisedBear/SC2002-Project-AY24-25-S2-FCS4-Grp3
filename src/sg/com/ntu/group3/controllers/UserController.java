package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.controllers.services.IUserService;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;
import sg.com.ntu.group3.views.AuthView;
import sg.com.ntu.group3.views.UserView;
import sg.com.ntu.group3.views.View;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/** User Controller Class responsible for handling generic User processes
 * <p>Such processes include logging in, and modifying user details.
 * This class does not handle any role-specific operations like application and registration.</p>
 */
public class UserController extends UserView implements IUserService {

    private AuthenticationService authenticationService;
    private Scanner scanner;
    private Session loginSession;

    /** Initialise a new userController by specifying the session and authentication service necessary for some of the methods used.
     * @param session The current session
     * @param authenticationService The authentication service for login and password change
     */
    public UserController(Session session, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.scanner = new Scanner(System.in);
        this.loginSession = session;

    }

    /** Implemented from IUserService, this is the login method that gathers user input and authenticates it using the authentication service.
     *
     * @return true if the login was successful, false otherwise
     * @throws IOException If no user is registered, throws IOException
     */
    public boolean login() throws IOException {
        String nric = AuthView.showLoginScreenNRIC();
        String password = AuthView.showLoginScreenPassword();
        // Search the userlist for a user with matching nric and password
        if (authenticationService.validateCredentials(nric, password)) {
            User user = this.authenticationService.findUserByNric(nric);
            loginSession.login(user);
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid NRIC or password.");
            return false;
        }
    }


    /** Method for displaying the user's information
     *
     */
    public void displayUserInfo() {
        if (loginSession.curLoggedIn()) {
            User user = loginSession.getCurrentUser();
            System.out.println("User Logged In Info:");
            System.out.println(user.getInfo());
            if (user instanceof HDBOfficer) {
                UserView.showApplicantBooking((Applicant) user);
                UserView.showOfficerProj((HDBOfficer) user);
            } else if (user instanceof Applicant) {
                UserView.showApplicantBooking((Applicant) user);
            } else if (user instanceof HDBManager) {
                UserView.showManagerInCharge((HDBManager) user);
            }
            View.lineSeparator();
        } else {
            System.out.println("No current session/user logged in");
        }

    }


    /** Implemented from IUserService, this method is used to allow the user to change their profile.
     *
     */
    public void updateUserInfo() {
        if (loginSession.curLoggedIn()) {
            User user = loginSession.getCurrentUser();
            Map.Entry<String, String> updateParameters = UserView.updateUserInfoForm();
            String parameter = updateParameters.getKey();
            switch (parameter) {
                case "Name": user.setName(updateParameters.getValue()); break;
                case "Age": user.setAge(Integer.parseInt(updateParameters.getValue())); break;
                case "maritalStatus": user.setMaritalStatus(updateParameters.getValue()); break;
                default: break;
            }
        } else {
            System.out.println("You must log in to update info.");
        }

    }


    /** Implemented from IUserService, this method is used to change the password of the user.
     * @return true if password change was successful, false if otherwise
     */
    public boolean changePassword() {
        boolean successful;
        User user = this.loginSession.getCurrentUser();
        if (loginSession.curLoggedIn()) {
            Map.Entry<String, String> oldpw_Newpw = UserView.showPasswordChangeForm();
            String oldpw = oldpw_Newpw.getKey();
            String newpw = oldpw_Newpw.getValue();
            successful = this.authenticationService.changePassword(user, oldpw, newpw);
        } else {
            System.out.println("Not logged in");
            return false;
        }
        UserView.passwordChangeResult(successful);
        return successful;
    }



}
