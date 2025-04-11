package sg.com.ntu.groupX.controllers;

import sg.com.ntu.groupX.controllers.services.IAuthenticationService;
import sg.com.ntu.groupX.controllers.services.IUserService;
import sg.com.ntu.groupX.roles.Applicant;
import sg.com.ntu.groupX.roles.User;
import sg.com.ntu.groupX.views.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController implements UserView, IAuthenticationService, IUserService {

    private IUserService userService;
    private Scanner scanner;
    private static List<User> userList;
    private Session loginSession;

    public UserController(IUserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        userList = new ArrayList<>();
        this.loginSession = new Session();
    }

    public boolean login(String nric, String password) {
        // Search the userlist for a user with matching nric and password
        User checkUser = userList.stream()
                .filter(user -> {
                    return user.getNric().equalsIgnoreCase(nric);
                })
                .findFirst().orElse(null);
        if (checkUser == null) {
            return false;
        } else if (checkUser.getPassword().equals(password)){
            loginSession.login(this, nric, password); //pass details of user who is logging in to initialized session
            return true;
        }
        return false;
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

    public void updateProfile(User user) {
        userService.updateProfile(user);
    }

    @Override
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

    @Override
    public void updateUserInfo() {
        if (loginSession.curLoggedIn()) {
            User user = loginSession.getCurrentUser();
            System.out.println("Enter new name:");
            String userNewName = scanner.nextLine();
            System.out.println("Change marital status (y/n)?");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                ((Applicant) user).setMaritalStatus();
            }
            user.setName(userNewName);
            System.out.println("Profile updated.");
        } else {
            System.out.println("You must log in to update info.");
        }

    }

    @Override
    public void showPasswordChangeForm() {
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
        

    }

    @Override
    public boolean validateCredentials(String nric, String password) {
        User found = findUser(nric);
        if(found!=null && found.getPassword().equals(password)){ // if we found the user and pw is correct
            return true;
        }
        else{
            return false;
        }
        
    }

    @Override
    public boolean validateNRIC(String nric) {
        return false;
    }

    @Override
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
