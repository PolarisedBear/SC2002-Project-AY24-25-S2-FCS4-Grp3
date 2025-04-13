package sg.com.ntu.group3.controllers;

import sg.com.ntu.group3.controllers.services.IWithdrawalService;
import sg.com.ntu.group3.models.Application;
import sg.com.ntu.group3.models.WithdrawalRequest;
import sg.com.ntu.group3.views.WithdrawalRequestView;

import java.util.List;

public class WithdrawalController implements IWithdrawalService {

    public WithdrawalController() {

    }

    public void submitWithdrawalRequest(Application application){

    }

    public void processWithdrawalRequest(WithdrawalRequest request, boolean approved) {

    }


    @Override
    public WithdrawalRequest createRequest(Application application) {
        return null;
    }

    @Override
    public List<WithdrawalRequest> getPendingRequests() {
        return List.of();
    }

    @Override
    public void approveRequest(WithdrawalRequest request) {

    }

    @Override
    public void rejectRequest(WithdrawalRequest request) {

    }
}
