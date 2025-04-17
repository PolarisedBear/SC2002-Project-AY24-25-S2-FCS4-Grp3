package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.roles.User;

import java.io.IOException;

public interface IAuthenticationService {
    boolean validateCredentials(String nric, String password);
    boolean validateNRIC(String nric);
    boolean changePassword(User user, String oldPassword, String newPassword);
    User findUserByNric(String nric) throws IOException;
}
