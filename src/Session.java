import java.util.Scanner;

public class Session extends UserController{
    private User currentUser;

    // When we start a new session, we start by logging in.
    public Session(IUserService userService) {
        super(userService);
        this.currentUser = null;
    }

    public void login(UserController controller, String nric, String password) {
        if (controller.login(nric, password)) {
            this.currentUser = controller.findUser(nric);
            System.out.println("User logged in: " + currentUser.getName());
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
