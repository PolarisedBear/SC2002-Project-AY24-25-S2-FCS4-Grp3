package sg.com.ntu.group3.views;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;

import java.util.List;

public interface WithdrawalRequestView {
    public void displayWithdrawalRequest(Application application);
    public void displayPendingRequests(List<WithdrawalRequest> requestList);
    public void showRequestStatus(WithdrawalRequest request);

}
