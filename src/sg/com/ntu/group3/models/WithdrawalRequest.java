package sg.com.ntu.group3.models;

import enums.WithdrawalStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** WithdrawalRequest class represents the data held by withdrawal requests created by applicants who wish to withdraw their application.
 * <p>Includes a static master list of all withdrawal requests made for filtering and searching.</p>
 */
public class WithdrawalRequest {
    private Application application;
    private Date requestDate;
    public WithdrawalStatus status;
    private static List<WithdrawalRequest> allWithdrawalRequests = new ArrayList<>();

    /** WithdrawalRequest Constructor. Automatically adds itself to the withdrawal request master list.
     * @param application The application to be withdrawn from
     * @param requestDate The date of the request
     * @param status The status of the newly created request
     */
    public WithdrawalRequest(Application application, Date requestDate, WithdrawalStatus status) {
        this.application = application;
        this.requestDate = requestDate;
        this.status = status;
        allWithdrawalRequests.add(this);
    }

    /** Method to approve this withdrawal request. Used by managers during withdrawal review
     *
     */
    public void approve() {
        this.status = WithdrawalStatus.APPROVED;
    }

    /** Method to reject this withdrawal request. Used by managers during withdrawal review
     *
     */
    public void reject() {
        this.status = WithdrawalStatus.REJECTED;
    }

    public static List<WithdrawalRequest> getAllWithdrawalRequests() {
        return allWithdrawalRequests;
    }

    public Application getApplication() {
        return this.application;
    }
}
