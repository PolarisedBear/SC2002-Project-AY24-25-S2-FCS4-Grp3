
import java.util.List;

public interface WithdrawalRequestView {
    public void displayWithdrawalRequest(Application application);
    public void displayPendingRequests(List<WithdrawalRequest> requestList);
    public void showRequestStatus(WithdrawalRequest request);
}
