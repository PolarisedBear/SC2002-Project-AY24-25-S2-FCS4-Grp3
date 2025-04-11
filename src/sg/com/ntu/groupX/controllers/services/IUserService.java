package sg.com.ntu.groupX.controllers.services;

public interface IUserService {
    public boolean login(String nric, String password);
    public boolean register(User user);
    public void updateProfile(User user);
}
