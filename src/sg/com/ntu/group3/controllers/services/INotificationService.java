package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.User;

public interface INotificationService {
    public void notifyStatusChange(User user, String message);
    public void notifyApplicationResult(Application application);
    public void notifyWithdrawalResult(WithdrawalRequest request);
}
