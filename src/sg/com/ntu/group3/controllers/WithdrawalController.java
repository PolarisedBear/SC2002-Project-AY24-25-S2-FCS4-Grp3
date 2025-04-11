package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IWithdrawalService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.views.WithdrawalRequestView;

import java.util.List;

public class WithdrawalController implements WithdrawalRequestView {
    private IWithdrawalService withdrawalService;

    public WithdrawalController(IWithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    public void submitWithdrawalRequest(Application application){

    }

    public void processWithdrawalRequest(WithdrawalRequest request, boolean approved) {

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
