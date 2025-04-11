package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.roles.User;

public class Session{
    private User currentUser;

    // When we start a new session, we start by logging in.
    public Session() {
        this.currentUser = null;
    }

//    if (gonna check the user type in csv)
//    currentUser = new sg.com.ntu.groupX.roles.Applicant()
//    currentUser = new sg.com.ntu.groupX.roles.HDBOfficer()

    public void login(UserController controller, String nric, String password) {
        if (controller.login(nric, password)) {
            this.currentUser = controller.findUser(nric);
            System.out.println("sg.com.ntu.groupX.roles.User logged in: " + currentUser.getName());
        }
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public boolean curLoggedIn(){
        return currentUser!=null;
    }
    public void logout(){
        this.currentUser = null;
        System.out.println("Logged out of current session");
    }
}
