package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.roles.User;

public interface IUserService {
    boolean login(String nric, String password);
    boolean register(User user);
    void updateProfile(User user);
}
