package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.roles.User;

import java.io.IOException;

/** User service interface including methods to login and change user profile details.
 * Implemented by UserController
 */
public interface IUserService {
    boolean login() throws IOException;
    boolean changePassword();
    void updateUserInfo();
}
