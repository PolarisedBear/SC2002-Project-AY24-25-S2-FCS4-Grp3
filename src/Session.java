import java.util.Scanner;

public class Session {
    private User currentUser;

    // When we start a new session, we start by logging in.
    public Session() {
        this.currentUser = null;
    }

    public void login(UserController controller, String nric, String password) {
        if (controller.login(nric, password)) {
            this.currentUser = controller.findUser(nric);
        }
    }
}
