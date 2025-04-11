package sg.com.ntu.group3.controllers.services;

public interface INotificationService {
    public void notifyStatusChange(User user, String message);
    public void notifyApplicationResult(Application application);
    public void notifyWithdrawalResult(WithdrawalRequest request);
}
