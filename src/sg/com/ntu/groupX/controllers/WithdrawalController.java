package sg.com.ntu.groupX.controllers;

import java.util.List;

public class WithdrawalController implements WithdrawalRequestView {
    private IWithdrawalService withdrawalService;

    public WithdrawalController(IWithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public void submitWithdrawalRequest(Application){

    }

    public void processWithdrawalRequest(WithdrawalRequest request, boolean approved) {

    }

    public void displayPendingRequests() {

    }

    @Override
    public void displayWithdrawalRequest(Application application) {

    }

    @Override
    public void displayPendingRequests(List<WithdrawalRequest> requestList) {

    }

    @Override
    public void showRequestStatus(WithdrawalRequest request) {

    }
}
