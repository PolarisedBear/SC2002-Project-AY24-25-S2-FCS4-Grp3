package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.AuthenticationService;
import sg.com.ntu.group3.roles.User;
import sg.com.ntu.group3.views.AuthView;
import sg.com.ntu.group3.views.UserView;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class UserController extends UserView{

    private AuthenticationService authenticationService;
    private Scanner scanner;
    private Session loginSession;

    public UserController(Session session, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.scanner = new Scanner(System.in);
        this.loginSession = session;

    }

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

    public boolean doesUserExist(User user) {
        // Search for existing user
        User userExists = findUser(user.getNric());
        if(userExists!=null){
            return false;
        }
        return true;
    }


    public void displayUserInfo() {
        if (loginSession.curLoggedIn()) {
            User user = loginSession.getCurrentUser();
            System.out.println("sg.com.ntu.groupX.roles.User Logged In Info:");
            System.out.println("Name: " + user.getName());
            System.out.println("NRIC: " + user.getNric());
        } else {
            System.out.println("No current session/user logged in");
        }

    }


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


   /* public void showPasswordChangeForm() {
        if(loginSession.curLoggedIn()){
            System.out.println("Changing password:");
            System.out.println("Enter old password:");
            String oldpw = scanner.nextLine();
            System.out.println("Changing password:");
            String newpw = scanner.nextLine();

            processPasswordChange(oldpw, newpw);
        }
        else{
            System.out.println("Not logged in");
        }
        
*/



    public boolean validateCredentials(String nric, String password) {
        User found = findUser(nric);
        if(found!=null && found.getPassword().equals(password)){ // if we found the user and pw is correct
            return true;
        }
        else{
            return false;
        }
        
    }



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

    public void processPasswordChange(String oldPassword, String newPassword) {
        if (loginSession.curLoggedIn()) {
            User currentUser = loginSession.getCurrentUser();
            if (changePassword()) {
                System.out.println("Password changed!");
            } 
            else {
                System.out.println("Your old password is not correct");
            }
        } 
        else {
            System.out.println("Not logged in");
        }

    }


    public User findUser(String nric) {
        return User.getUserList().stream()
                .filter(user -> user.getNric().equalsIgnoreCase(nric))
                .findFirst().orElse(null);
    }

    public void logout(){
        loginSession.logout();
    }
}
