import enums.WithdrawalStatus;

import java.util.Date;

public class WithdrawalRequest {
    private Application application;
    private Date requestDate;
    private WithdrawalStatus status;

    public WithdrawalRequest(Application application, Date requestDate, WithdrawalStatus status) {
        this.application = application;
        this.requestDate = requestDate;
        this.status = status;
    }

    public void approve() {
        this.status = WithdrawalStatus.APPROVED;
    }

    public void reject() {
        this.status = WithdrawalStatus.REJECTED;
    }
}
