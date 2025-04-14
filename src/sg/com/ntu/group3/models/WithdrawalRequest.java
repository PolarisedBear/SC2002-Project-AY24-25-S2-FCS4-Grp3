package sg.com.ntu.group3.models;

import enums.WithdrawalStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WithdrawalRequest {
    private Application application;
    private Date requestDate;
    private WithdrawalStatus status;
    private static List<WithdrawalRequest> allWithdrawalRequests = new ArrayList<>();

    public WithdrawalRequest(Application application, Date requestDate, WithdrawalStatus status) {
        this.application = application;
        this.requestDate = requestDate;
        this.status = status;
        allWithdrawalRequests.add(this);
    }

    public void approve() {
        this.status = WithdrawalStatus.APPROVED;
    }

    public void reject() {
        this.status = WithdrawalStatus.REJECTED;
    }

    public static List<WithdrawalRequest> getAllWithdrawalRequests() {
        return allWithdrawalRequests;
    }
}
