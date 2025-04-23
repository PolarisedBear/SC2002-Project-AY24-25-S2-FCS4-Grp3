package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.UserRepository;
import sg.com.ntu.group3.roles.Applicant;
import sg.com.ntu.group3.roles.HDBManager;
import sg.com.ntu.group3.roles.HDBOfficer;
import sg.com.ntu.group3.roles.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/** Class for verification and search of user details
 *
 */
public class AuthenticationService implements IAuthenticationService {

    public AuthenticationService() {
    }

    /** Method to check if the given NRIC and password match any current users'
     * @param nric String NRIC following the correct NRIC format, not case-sensitive
     * @param password String password, case-sensitive
     * @return boolean true if given details match a user, false if no user exists
     */
    @Override
    public boolean validateCredentials(String nric, String password) {
        try {
            User user = findUserByNric(nric);
            return user != null && user.getPassword().equals(password);
        } catch (IOException e) {
            return false;
        }
    }

    /** Method to check if a given String NRIC matches the correct NRIC format
     * @param nric String NRIC to be tested
     * @return true if the NRIC is the correct format, false if otherwise
     */
    @Override
    public boolean validateNRIC(String nric) {
        // Define the nric string
        String regex = "^[STFG]\\d{7}[A-Z]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nric);

        return matcher.matches(); // Returns true if nric uses the correct format
    }

    /** Method to change a user's password, only if the entered old password is correct
     * @param user User object to be updated
     * @param oldPassword String value of the old password to be checked
     * @param newPassword String value of the new password
     * @return true if password change is successful, false is old password is incorrect
     */
    @Override
    public boolean changePassword(User user, String oldPassword, String newPassword) {
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    /** Method to search for a user by their NRIC
     * @param nric String NRIC to search for
     * @return the User object of which the given NRIC belongs to, null if no such user was found
     *
     */
    public User findUserByNric(String nric) throws IOException {

        for (User user : Stream.of(User.getUserList()).flatMap(List::stream).toList()) {
            if (user.getNric().equalsIgnoreCase(nric)) {
                return user;
            }
        }
        return null;
    }


    /** Method to load user data from 3 csv files containing the credentials for applicants, managers and officers.
     * @throws IOException if file format is incorrect
     */
    public void registerFromExcel() throws IOException {
        List<Applicant> applicants = UserRepository.getAllApplicants();
        List<HDBOfficer> officers = UserRepository.getAllOfficers();
        List<HDBManager> managers = UserRepository.getAllManagers();
    }

    /** Method to check if a given String input follows a given Date format
     * @param input The String input to be checked
     * @param sdf The SimpleDateFormat object to be checked against
     * @return true if the input String is correctly formatted, false if otherwise
     */
    public static boolean isValidDate(String input, SimpleDateFormat sdf) {
        sdf.setLenient(false);
        try {
            sdf.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }



}
