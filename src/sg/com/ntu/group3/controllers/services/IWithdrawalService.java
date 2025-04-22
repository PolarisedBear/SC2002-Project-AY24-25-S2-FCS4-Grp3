package sg.com.ntu.group3.controllers.services;

import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.roles.HDBManager;

import java.util.List;

/** Withdrawal Service interface including methods for modifying and retrieving withdrawal requests
 * Implemented by WithdrawalController
 *
 */
public interface IWithdrawalService {
    List<WithdrawalRequest> getPendingRequests(HDBManager manager);
    void approveRequest(WithdrawalRequest request);
    void rejectRequest(WithdrawalRequest request);
}
