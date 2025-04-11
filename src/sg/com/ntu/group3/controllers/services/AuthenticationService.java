package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.UserRepository;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AuthenticationService implements IAuthenticationService {

    public AuthenticationService() {
    }

    @Override
    public boolean validateCredentials(String nric, String password) {
        try {
            User user = findUserByNric(nric);
            return user != null && user.getPassword().equals(password);
        } catch (IOException e) {
            return false;
        }
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

    public User findUserByNric(String nric) throws IOException {
        List<Applicant> applicants = UserRepository.getAllApplicants();
        List<HDBOfficer> officers = UserRepository.getAllOfficers();
        List<HDBManager> managers = UserRepository.getAllManagers();

        for (User user : Stream.of(applicants, officers, managers).flatMap(List::stream).toList()) {
            if (user.getNric().equalsIgnoreCase(nric)) {
                return user;
            }
        }
        return null;
    }


}
