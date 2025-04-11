package sg.com.ntu.groupX.controllers.services;

import java.util.List;

public interface IWithdrawalService {
    public WithdrawalRequest createRequest(Application application);
    public List<WithdrawalRequest> getPendingRequests();
    public void approveRequest(WithdrawalRequest request);
    public void rejectRequest(WithdrawalRequest request);
}
