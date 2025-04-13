package sg.com.ntu.groupX.controllers;

import sg.com.ntu.groupX.controllers.services.IWithdrawalService;
import sg.com.ntu.groupX.models.Application;
import sg.com.ntu.groupX.models.WithdrawalRequest;
import sg.com.ntu.groupX.views.WithdrawalRequestView;

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
