package sg.com.ntu.group3.controllers;

import enums.Role;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

/** Session Class handles the tracking and granting of permissions to the current user during runtime.
 * <p>Only one user can be logged in at any time</p>
 */
public class Session{
    private User currentUser;
    private Role role;


    /** When a new session is called, it is by default in the logged out state, meaning no user is assigned to this session.
     *
     */
    public Session() {
        this.currentUser = null;
        this.role = Role.NONE;
    }

    /** Method for assigning roles to the current user for tracking and granting permissions.
     * This is used so the Main driver can switch the display screens accordingly.
     *
     */
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

    /** Login method, this method assigns a User object to be the current User.
     * @param user The User object to be logged in.
     */
    public void login(User user) {
        this.currentUser = user;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public Applicant getCurrentApplicant() {return (Applicant) currentUser;}
    public HDBOfficer getCurrentHDBOfficer() {return (HDBOfficer) currentUser;}
    public HDBManager getCurrentHDBManager() {return (HDBManager) currentUser;}

    /** Method to check if there is a user currently logged in
     * @return true if a user was found, false if otherwise.
     */
    public boolean curLoggedIn(){
        return currentUser!=null;
    }


    /** Method to log out of current session
     * Sets current User to null.
     */
    public void logout(){
        this.currentUser = null;
        this.role = Role.NONE;
        System.out.println("Logged out of current session");
    }

}
