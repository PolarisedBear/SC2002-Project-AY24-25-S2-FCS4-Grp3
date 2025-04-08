public interface IAuthenticationService {
    public boolean validateCredentials(String nric, String password);
    public boolean validateNRIC(String nric);
    public boolean changePassword(User user, String oldPassword, String newPassword);
}
