package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IAuthenticationService;
import sg.com.ntu.group3.controllers.services.IUserService;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.User;
import sg.com.ntu.group3.views.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserController {

    private IAuthenticationService iauthService;
    private IUserService iuserService;
    private Scanner scanner;
    private static List<User> userList;
    private Session loginSession;

    public UserController(IUserService iuserService, IAuthenticationService iauthService) {
        this.iuserService = iuserService;
        this.iauthService = iauthService;
        this.scanner = new Scanner(System.in);
        userList = new ArrayList<>();
        this.loginSession = new Session();

    }

    public boolean login(String nric, String password) {
        // Search the userlist for a user with matching nric and password
        if (iauthService.validateCredentials(nric, password)) {
            User user = iauthService.findUserByNric(nric);
            loginSession.login(user);
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid NRIC or password.");
            return false;
        }
    }

    public boolean register(User user) {
        // Search for existing user
        User userExists = findUser(user.getNric());
        if(userExists!=null){
            return false;
        }
        userList.add(user);
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
            Map.Entry<String, String> updateParameters = UserView.updateUserInfo();
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


    public boolean validateNRIC(String nric) {
        return false;
    }


    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public void processPasswordChange(String oldPassword, String newPassword) {
        if (loginSession.curLoggedIn()) {
            User currentUser = loginSession.getCurrentUser();
            if (changePassword(currentUser, oldPassword, newPassword)) {
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
        return userList.stream()
                .filter(user -> user.getNric().equalsIgnoreCase(nric))
                .findFirst().orElse(null);
    }

    public void logout(){
        loginSession.logout();
    }
}
