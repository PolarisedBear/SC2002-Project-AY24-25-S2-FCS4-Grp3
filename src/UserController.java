public class UserController implements UserView, IAuthenticationService {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public void login(String nric, String password) {
        boolean login = userService.login(nric, password);
        if (login) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Login Failed!");
        }
    }

    public void register(User user) {
        boolean register = userService.register(user);
        if (register) {
            System.out.println("Registration Successful!");
        } else {
            System.out.println("Registration Failed!");
        }
    }

    public void updateProfile(User user) {
        userService.updateProfile(user);
    }

    @Override
    public void displayUserInfo() {

    }

    @Override
    public void updateUserInfo() {

    }

    @Override
    public void showPasswordChangeForm() {

    }

    @Override
    public boolean validateCredentials(String nric, String password) {
        return false;
    }

    @Override
    public boolean validateNRIC(String nric) {
        return false;
    }

    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        return false;
    }

    public void processPasswordChange(String oldPassword, String newPassword) {

    }
}
