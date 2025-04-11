package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;

import java.util.List;

public interface IWithdrawalService {
    public WithdrawalRequest createRequest(Application application);
    public List<WithdrawalRequest> getPendingRequests();
    public void approveRequest(WithdrawalRequest request);
    public void rejectRequest(WithdrawalRequest request);
}
