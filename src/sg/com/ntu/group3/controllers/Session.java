package sg.com.ntu.group3.controllers;

import enums.Role;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

public class Session{
    private User currentUser;
    private Role role;

    // When we start a new session, we start by logging in.
    public Session() {
        this.currentUser = null;
        this.role = Role.NONE;
    }

//    if (gonna check the user type in csv)
//    currentUser = new sg.com.ntu.groupX.roles.Applicant()
//    currentUser = new sg.com.ntu.groupX.roles.HDBOfficer()
    public void gainAccess() {
        if (this.currentUser instanceof HDBOfficer) {
            this.role = Role.OFFICER;
        } else if (this.currentUser instanceof HDBManager) {
            this.role = Role.MANAGER;
        } else if (this.currentUser instanceof Applicant) {
        this.role = Role.APPLICANT;
        }
    }

    public Role getRole() {
        return this.role;
    }


    public void login(User user) {
        this.currentUser = user;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public Applicant getCurrentApplicant() {return (Applicant) currentUser;}
    public HDBOfficer getCurrentHDBOfficer() {return (HDBOfficer) currentUser;}
    public HDBManager getCurrentHDBManager() {return (HDBManager) currentUser;}
    public boolean curLoggedIn(){
        return currentUser!=null;
    }
    public void logout(){
        this.currentUser = null;
        System.out.println("Logged out of current session");
    }

}
