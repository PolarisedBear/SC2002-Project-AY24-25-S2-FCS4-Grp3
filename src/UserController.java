import java.util.ArrayList;
import java.util.List;

public class UserController implements UserView, IAuthenticationService, IUserService {

    private IUserService userService;
    private static List<User> userList;

    public UserController(IUserService userService) {
        this.userService = userService;
        userList = new ArrayList<>();
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
            return true;
        }
        return false;
    }

    public boolean register(User user) {
        // Search for existing user

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

    public User findUser(String nric) {
        return userList.stream()
                .filter(user -> user.getNric().equalsIgnoreCase(nric))
                .findFirst().orElse(null);
    }
}
