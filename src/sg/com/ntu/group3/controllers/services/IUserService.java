package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.roles.User;

public interface IUserService {
    public boolean login(String nric, String password);
    public boolean register(User user);
    public void updateProfile(User user);
}
