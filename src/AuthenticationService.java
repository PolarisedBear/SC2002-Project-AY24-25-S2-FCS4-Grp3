import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthenticationService implements IAuthenticationService {

    public AuthenticationService() {
    }

    @Override
    public boolean validateCredentials(String nric, String password) {
        return false;
    }

    @Override
    public boolean validateNRIC(String nric) {
        // Define the nric string
        String regex = "^[STFG]\\d{7}[A-Z]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nric);

        return matcher.matches(); // Returns true if nric uses the correct format
    }

    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }
}
