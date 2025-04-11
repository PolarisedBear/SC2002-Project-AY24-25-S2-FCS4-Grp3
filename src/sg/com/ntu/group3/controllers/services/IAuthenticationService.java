package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.roles.User;

import java.io.IOException;

public interface IAuthenticationService {
    public boolean validateCredentials(String nric, String password);
    public boolean validateNRIC(String nric);
    public boolean changePassword(User user, String oldPassword, String newPassword);
    public User findUserByNric(String nric) throws IOException;
}
